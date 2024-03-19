package com.example.schoolmangement.service

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.TeacherDto
import com.example.schoolmangement.model.Teacher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface TeacherService {
    fun index(name:String? , page :Int , size :Int) : PageResponse<TeacherDto?>
    fun show(id : Long):ObjectResponse<TeacherDto>
    fun deleteById(id:Long) : MessageResponse
    fun save(newTeacher : Teacher , id:Long) : MessageResponse
    fun updateById(updatedTeacher : TeacherDto, id:Long) : MessageResponse
}