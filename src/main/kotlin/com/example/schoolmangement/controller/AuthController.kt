package com.example.schoolmangement.controller

import com.example.demo.dto.LoginResponseDto
import com.example.schoolmangement.dto.LoginRequestDto
import com.example.schoolmangement.dto.RefreshTokenRequestDto
import com.example.schoolmangement.dto.TokenResponseDto
import com.example.schoolmangement.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController (private val authenticationService : AuthenticationService) {
    @PostMapping
    fun authenticate(@RequestBody loginRequest: LoginRequestDto) : LoginResponseDto {
        return authenticationService.authenticate(loginRequest.username , loginRequest.password)
    }
    @PostMapping("/refreshToken")
    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequestDto) : TokenResponseDto{
        return authenticationService.refreshToken(refreshToken = refreshTokenRequest.token!!)
    }
}