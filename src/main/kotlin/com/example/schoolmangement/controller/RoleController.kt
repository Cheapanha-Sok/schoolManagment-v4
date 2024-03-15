package com.example.schoolmangement.controller

import com.example.schoolmangement.model.Role
import com.example.schoolmangement.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
@RequestMapping("/api/v1/role")
class RoleController(private val roleService:RoleService) {
    @GetMapping
    fun index(): Iterable<Role> {
        return roleService.index()
    }

    @PostMapping
    fun save(@RequestBody newRole: Role): ResponseEntity<HttpStatus> {
        return roleService.save(newRole)
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): Role {
        return roleService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<HttpStatus> {
        return roleService.deleteById(id)
    }
    @PutMapping("{id}")
    fun updateById(@RequestBody updatedRole : Role , @PathVariable("id") id:Long) : Role{
        return roleService.updateById(updatedRole , id)
    }


}