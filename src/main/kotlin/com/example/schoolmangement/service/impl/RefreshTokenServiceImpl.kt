package com.example.schoolmangement.service.impl

import com.example.schoolmangement.configuration.JwtProperties
import com.example.schoolmangement.dto.TokenResponseDto
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Account
import com.example.schoolmangement.model.RefreshToken
import com.example.schoolmangement.repository.AccountRepository
import com.example.schoolmangement.repository.RefreshTokenRepository
import com.example.schoolmangement.service.RefreshTokenService
import com.example.schoolmangement.service.TokenService
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class RefreshTokenServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtProperties: JwtProperties,
    private val accountRepository: AccountRepository,
    private val tokenService: TokenService
) : RefreshTokenService {
    override fun createRefreshToken(username: String) {
        val refreshToken = RefreshToken()
        val account: Optional<Account> = accountRepository.findByName(username)
        if (account.isPresent) {
            refreshToken.account = account.get()
            refreshToken.token = (UUID.randomUUID().toString())
            refreshToken.expiryDate = Instant.now().plusMillis(jwtProperties.refreshTokenExpiration)
            refreshTokenRepository.save(refreshToken)
        } else
            throw NotFoundException("Username with $username not found")
    }

    override fun findByToken(token: String): Optional<RefreshToken> {
        return refreshTokenRepository.findByToken(token)
    }

    override fun verifyExpiration(refreshToken: String): RefreshToken {
        val token = findByToken(refreshToken)
        if (token.isPresent){
            if (token.get().expiryDate!! < Instant.now()){
                refreshTokenRepository.delete(token.get())
                throw RuntimeException("$refreshToken Refresh token is expired. Please make a new login..!")
            }
            else
                return token.get()
        }else
            throw RuntimeException("$refreshToken is invalid")
    }

    override fun findByAccount(username: String): TokenResponseDto {
        val account: Optional<Account> = accountRepository.findByName(username)
        if (account.isPresent) {
            val refreshToken = refreshTokenRepository.findByAccount(account.get())
            if (refreshToken.isPresent) {
                val accessToken: String = tokenService.generate(
                    account.get(),
                    Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
                )
                return TokenResponseDto(accessToken = accessToken)
            }
            throw NotFoundException("Refresh not create with this username ${account.get().username} yet")
        } else
            throw NotFoundException("Username with $username not found")
    }

    override fun getRefreshToken(username: String): String {
        val account = accountRepository.findByName(username)
        if (account.isPresent){
            val refreshToken = refreshTokenRepository.findByAccount(account.get())
            if (refreshToken.isPresent){
                return refreshToken.get().token!!
            }
        }
        throw NotFoundException("Username with $username not found")
    }
}
