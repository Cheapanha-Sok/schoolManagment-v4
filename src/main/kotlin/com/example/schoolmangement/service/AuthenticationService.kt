package com.example.schoolmangement.service

import com.example.demo.dto.LoginResponseDto
import com.example.schoolmangement.dto.TokenResponseDto

interface AuthenticationService {
    fun authenticate(username :String , password :String) : LoginResponseDto
    fun refreshToken(refreshToken :String) : TokenResponseDto
}