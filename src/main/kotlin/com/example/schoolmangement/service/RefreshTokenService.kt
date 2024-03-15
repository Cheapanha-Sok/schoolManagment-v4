package com.example.schoolmangement.service

import com.example.schoolmangement.dto.TokenResponseDto
import com.example.schoolmangement.model.RefreshToken
import java.util.Optional

interface RefreshTokenService {
    fun createRefreshToken(username :String)
    fun findByToken(token :String) : Optional<RefreshToken>
    fun verifyExpiration( refreshToken: String) : RefreshToken
    fun findByAccount(username :String) : TokenResponseDto
    fun getRefreshToken(username : String) :String
}