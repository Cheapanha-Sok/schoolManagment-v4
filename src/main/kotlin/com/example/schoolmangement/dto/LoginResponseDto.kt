package com.example.demo.dto

data class LoginResponseDto(
    var accessToken:String,
    var refreshToken :String,
    var username :String? = null,
    var role :List<String>? = null,
) {
}