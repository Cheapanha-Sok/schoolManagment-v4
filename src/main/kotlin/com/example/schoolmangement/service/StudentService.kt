package com.example.schoolmangement.service

import com.example.schoolmangement.dto.StudentDto
import com.example.schoolmangement.model.Student
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface StudentService {
    fun index() : Iterable<StudentDto>
    fun show(id : Long): StudentDto
    fun deleteById(id:Long) :ResponseEntity<HttpStatus>
    fun save(newStudent : Student , id:Long) : ResponseEntity<HttpStatus>
    fun updateById(updatedStudent : StudentDto, id:Long) : StudentDto
}