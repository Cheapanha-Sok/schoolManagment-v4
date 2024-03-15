package com.example.schoolmangement.service

import com.example.schoolmangement.dto.DepartmentDto
import com.example.schoolmangement.model.Department
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface DepartmentService {
    fun index(): Iterable<DepartmentDto>
    fun show(id: Long): DepartmentDto
    fun deleteById(id: Long): ResponseEntity<HttpStatus>
    fun save(newDepartment: Department, id: Long): ResponseEntity<HttpStatus>
    fun updateById(updatedDepartment: DepartmentDto, id: Long): DepartmentDto
}