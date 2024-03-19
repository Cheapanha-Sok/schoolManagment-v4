package com.example.schoolmangement.controller

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.model.Role
import com.example.schoolmangement.service.RoleService
import com.example.schoolmangement.util.contants.Constant
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("${Constant.MAIN_URL}role")
class RoleController(private val roleService:RoleService) {
    @GetMapping
    fun index(): ObjectResponse<List<Role>> {
        return roleService.index()
    }

    @PostMapping
    fun save(@RequestBody newRole: Role): MessageResponse {
        return roleService.save(newRole)
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): ObjectResponse<Role> {
        return roleService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): MessageResponse {
        return roleService.deleteById(id)
    }
    @PutMapping("{id}")
    fun updateById(@RequestBody updatedRole : Role , @PathVariable("id") id:Long) : MessageResponse{
        return roleService.updateById(updatedRole , id)
    }


}