package com.example.schoolmangement.dto

data class DepartmentDto(
    var id :Long,
    var name :String?=null,
    var headName : String?=null,
    var office :String?=null,
    var faculty : String?=null
)
