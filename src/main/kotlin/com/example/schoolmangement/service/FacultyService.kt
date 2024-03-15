package com.example.schoolmangement.service

import com.example.schoolmangement.model.Faculty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface FacultyService {
    fun show(id : Long) : Faculty
    fun index() :Iterable<Faculty>
    fun deleteById(id:Long) : ResponseEntity<HttpStatus>
    fun save(newFaculty: Faculty) : ResponseEntity<HttpStatus>
    fun updateById(updatedFaculty: Faculty, id:Long) : Faculty
}