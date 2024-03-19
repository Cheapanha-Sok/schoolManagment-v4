package com.example.schoolmangement.service

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.model.Faculty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface FacultyService {
    fun show(id : Long) : ObjectResponse<Faculty>
    fun index() : ObjectResponse<List<Faculty>>
    fun deleteById(id:Long) : MessageResponse
    fun save(newFaculty: Faculty) : MessageResponse
    fun updateById(updatedFaculty: Faculty, id:Long) : MessageResponse
}