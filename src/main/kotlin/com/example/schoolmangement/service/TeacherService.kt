package com.example.schoolmangement.service

import com.example.schoolmangement.dto.TeacherDto
import com.example.schoolmangement.model.Teacher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface TeacherService {
    fun index() : Iterable<TeacherDto>
    fun show(id : Long):TeacherDto
    fun deleteById(id:Long) : ResponseEntity<HttpStatus>
    fun save(newTeacher : Teacher , id:Long) : ResponseEntity<HttpStatus>
    fun updateById(updatedTeacher : TeacherDto, id:Long) : TeacherDto
}