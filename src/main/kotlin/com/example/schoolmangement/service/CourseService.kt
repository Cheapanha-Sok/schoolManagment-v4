package com.example.schoolmangement.service

import com.example.schoolmangement.dto.CourseDto
import com.example.schoolmangement.model.Course
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface CourseService {
    fun index() : Iterable<CourseDto>
    fun show(id : Long) : CourseDto
    fun deleteById(id:Long) : ResponseEntity<HttpStatus>
    fun save(newCourse: Course , id: Long) : ResponseEntity<HttpStatus>
    fun updateById(updatedCourse : CourseDto, id:Long) : CourseDto
}