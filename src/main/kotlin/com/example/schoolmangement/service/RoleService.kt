package com.example.schoolmangement.service

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.model.Role
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface RoleService {
    fun index() : ObjectResponse<List<Role>>
    fun show(id : Long): ObjectResponse<Role>
    fun deleteById(id:Long) : MessageResponse
    fun save(newRole : Role) : MessageResponse
    fun updateById(updatedRole : Role, id:Long) : MessageResponse
}