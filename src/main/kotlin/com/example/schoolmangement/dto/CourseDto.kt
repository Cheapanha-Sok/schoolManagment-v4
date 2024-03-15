package com.example.schoolmangement.dto

data class CourseDto(
    var id:Long,
    var name :String,
    var credit :Float,
    var type :String,
    var departments :List<String>
)