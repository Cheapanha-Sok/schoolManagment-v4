package com.example.schoolmangement.service.impl

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Role
import com.example.schoolmangement.repository.RoleRepository
import com.example.schoolmangement.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun show(id: Long): ObjectResponse<Role> {
        val role: Role = roleRepository.findById(id)!!.orElseThrow { NotFoundException("Role with id $id not found") }
        return ObjectResponse(role)
    }

    override fun index(): ObjectResponse<List<Role>> {
        return ObjectResponse(roleRepository.findAll())
    }

    override fun deleteById(id: Long): MessageResponse {
        val role : Role = roleRepository.findById(id)!!.orElseThrow {NotFoundException("Role with id $id not found")}
        roleRepository.delete(role).let { return MessageResponse() }
    }

    override fun save(newRole: Role): MessageResponse {
        roleRepository.save(newRole)
        return MessageResponse()
    }

    override fun updateById(updatedRole: Role, id: Long): MessageResponse {
        TODO("Not yet implemented")
    }
}