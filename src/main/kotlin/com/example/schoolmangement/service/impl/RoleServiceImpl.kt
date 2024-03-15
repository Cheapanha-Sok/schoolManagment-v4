package com.example.schoolmangement.service.impl

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
    override fun show(id: Long): Role {
        val role: Optional<Role> = roleRepository.findById(id)
        if (role.isPresent) {
            return role.get()
        } else
            throw NotFoundException("Role with id $id not found")
    }

    override fun index(): Iterable<Role> {
        return roleRepository.findAll()
    }

    override fun deleteById(id: Long): ResponseEntity<HttpStatus> {
        val isExist: Boolean = roleRepository.existsById(id)
        if (isExist) {
            roleRepository.deleteById(id)
            return ResponseEntity.ok().build()
        } else
            throw NotFoundException("Role with id $id not found")
    }

    override fun save(newRole: Role): ResponseEntity<HttpStatus> {
        roleRepository.save(newRole)
        return ResponseEntity.ok().build()
    }

    override fun updateById(updatedRole: Role, id: Long): Role {
        TODO("Not yet implemented")
    }
}