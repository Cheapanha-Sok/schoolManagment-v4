package com.example.schoolmangement.dto

data class TeacherDto(
    var id :Long ? =null,
    var name:String ?=null,
    var gender:String ?=null,
    var address :String ? =null,
    var phoneNumber:String ?=null,
    var courses: List<String> ?=null,
    var account:List<String>? =null,


)