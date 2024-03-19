package com.example.schoolmangement.service

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.StudentDto
import com.example.schoolmangement.model.Student
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface StudentService {
    fun index(name:String ?, page :Int , size :Int) : PageResponse<StudentDto?>
    fun show(id : Long): ObjectResponse<StudentDto>
    fun deleteById(id:Long) :MessageResponse
    fun save(newStudent : Student , id:Long) : MessageResponse
    fun updateById(updatedStudent : StudentDto, id:Long) : MessageResponse
}