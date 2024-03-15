package com.example.schoolmangement.service

import com.example.schoolmangement.model.Role
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface RoleService {
    fun index() : Iterable<Role>
    fun show(id : Long): Role
    fun deleteById(id:Long) : ResponseEntity<HttpStatus>
    fun save(newRole : Role) : ResponseEntity<HttpStatus>
    fun updateById(updatedRole : Role, id:Long) : Role
}