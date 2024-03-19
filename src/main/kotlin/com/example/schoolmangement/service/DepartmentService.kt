package com.example.schoolmangement.service

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.DepartmentDto
import com.example.schoolmangement.model.Department
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface DepartmentService {
    fun index(name:String? , page :Int , size :Int): PageResponse<DepartmentDto?>
    fun show(id: Long): ObjectResponse<DepartmentDto>
    fun deleteById(id: Long): MessageResponse
    fun save(newDepartment: Department, id: Long): MessageResponse
    fun updateById(updatedDepartment: DepartmentDto, id: Long): MessageResponse
}