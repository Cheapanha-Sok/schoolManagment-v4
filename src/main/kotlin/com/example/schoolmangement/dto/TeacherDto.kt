package com.example.schoolmangement.dto

data class TeacherDto(
    var id :Long,
    var name:String,
    var gender:String,
    var address :String,
    var phoneNumber:String,
    var courses: List<String>,
    var account:List<String>?,


)