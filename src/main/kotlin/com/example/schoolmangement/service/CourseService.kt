package com.example.schoolmangement.service

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.CourseDto
import com.example.schoolmangement.model.Course
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface CourseService {
    fun index(name :String? , page :Int , size :Int) : PageResponse<CourseDto?>
    fun show(id : Long) : ObjectResponse<CourseDto>
    fun deleteById(id:Long) : MessageResponse
    fun save(newCourse: Course , id: Long) : MessageResponse
    fun updateById(updatedCourse : CourseDto, id:Long) : MessageResponse
}