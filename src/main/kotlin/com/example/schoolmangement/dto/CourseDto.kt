package com.example.schoolmangement.dto

data class CourseDto(
    var id:Long? = null,
    var name :String ? =null,
    var credit :Float ? =null,
    var type :String ? =null,
    var departments :List<String>?=null
)