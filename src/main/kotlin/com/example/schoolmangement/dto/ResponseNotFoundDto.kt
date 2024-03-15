package com.example.schoolmangement.dto

import java.time.LocalTime

data class ResponseNotFoundDto(
    val status :Int ? =null ,
    val message :String?=null ,
    val localTime: LocalTime? =null
)