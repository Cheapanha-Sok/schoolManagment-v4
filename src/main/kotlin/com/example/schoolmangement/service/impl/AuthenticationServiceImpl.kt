package com.example.schoolmangement.service.impl

import com.example.demo.dto.LoginResponseDto
import com.example.schoolmangement.configuration.JwtProperties
import com.example.schoolmangement.dto.TokenResponseDto
import com.example.schoolmangement.repository.AccountRepository
import com.example.schoolmangement.service.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationServiceImpl(
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenService: RefreshTokenService,
) : AuthenticationService {
    override fun authenticate(username: String, password: String): LoginResponseDto {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                username, password
            )
        )
        val user = userDetailsService.loadUserByUsername(username)
        val accessToken :String = generateAccessToken(user)
        val refreshToken :String = refreshTokenService.getRefreshToken(username)

        return LoginResponseDto(
            accessToken = accessToken,
            refreshToken = refreshToken,
            username = user.username,
            role = user.authorities.map { it.authority }
        )
    }

    override fun refreshToken(refreshToken: String): TokenResponseDto {
        val newToken = refreshTokenService.getNewToken(refreshToken)
        return TokenResponseDto(
            newToken
        )
    }
    private fun generateAccessToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    )
}