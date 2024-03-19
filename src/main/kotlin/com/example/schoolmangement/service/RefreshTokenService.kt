package com.example.schoolmangement.service

import com.example.schoolmangement.model.Account
import com.example.schoolmangement.model.RefreshToken
import java.time.Instant

interface RefreshTokenService {
    fun createRefreshToken(account :Account):RefreshToken
    fun findByToken(token :String) : RefreshToken
    fun verifyExpiration(date: Instant): Boolean
    fun getNewToken(refreshToken :String) : String
    fun getRefreshToken(username :String) :String
}