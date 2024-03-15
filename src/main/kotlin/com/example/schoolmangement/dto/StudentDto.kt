package com.example.schoolmangement.dto

data class StudentDto(
    var id :Long,
    var name :String,
    var gender :String,
    var phoneNumber :String,
    var address :String,
    var generation :Int,
//    var age :Int,
    var year :Int,
    var degree :String,
    var department: List<String>,
    var account :String?
)