package com.example.schoolmangement.dto

data class StudentDto(
    var id :Long?=null,
    var name :String?=null,
    var gender :String?=null,
    var phoneNumber :String?=null,
    var address :String?=null,
    var generation :Int?=null,
//    var age :Int,
    var year :Int?=null,
    var degree :String?=null,
    var department: List<String>?=null,
    var account :String?=null
)