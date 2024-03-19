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

    override fun createRefreshToken(account: Account): RefreshToken {
        val refreshToken = RefreshToken(
            account = account,
            token = UUID.randomUUID().toString(),
            expiryDate = Instant.now().plusMillis(jwtProperties.refreshTokenExpiration)
        )
        refreshTokenRepository.save(refreshToken).let { return refreshToken }

    }
    override fun findByToken(token: String): RefreshToken {
        return refreshTokenRepository.findByToken(token).orElseThrow {
            NotFoundException("Refresh token not found with token: $token")
        }
    }

    override fun verifyExpiration(date: Instant): Boolean {
        return date.isAfter(Instant.now())
    }

    override fun getNewToken(refreshToken: String): String {
        val token = findByToken(refreshToken)

        if (!verifyExpiration(token.expiryDate!!)) {
            refreshTokenRepository.delete(token)
            throw RuntimeException("Refresh token has expired. Please make a new login.")
        }

        val account = accountRepository.findById(token.account!!.id!!)?.orElseThrow {
            NotFoundException("Account associated with the token not found")
        }

        return tokenService.generate(account!!, Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration))
    }

    override fun getRefreshToken(username: String): String {
        val account = accountRepository.findByName(username).orElseThrow {
            NotFoundException("Username not found with name $username")
        }

        val refreshToken = refreshTokenRepository.findByAccount(account).orElseGet {
            createRefreshToken(account)
        }
        return refreshToken.token ?: throw NotFoundException("Refresh token not found")
    }
}
