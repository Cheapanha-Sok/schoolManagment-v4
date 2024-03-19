package com.example.schoolmangement.controller


import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.DepartmentDto
import com.example.schoolmangement.model.Department
import com.example.schoolmangement.service.DepartmentService
import com.example.schoolmangement.util.contants.Constant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${Constant.MAIN_URL}department")
class DepartmentController(private val departmentService: DepartmentService) {
    @GetMapping
    fun index(
        @RequestParam(required = false) name: String? = null,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PageResponse<DepartmentDto?> {
        return departmentService.index(name , page , size)
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): ObjectResponse<DepartmentDto> {
        return departmentService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): MessageResponse {
        return departmentService.deleteById(id)
    }

    @PostMapping("{faculty_id}")
    fun save(@RequestBody newDepartment: Department, @PathVariable("faculty_id") id: Long): MessageResponse {
        return departmentService.save(newDepartment, id)
    }

    @PutMapping("{id}")
    fun updateById(@RequestBody updatedDepartment: DepartmentDto, @PathVariable("id") id: Long): MessageResponse {
        return departmentService.updateById(updatedDepartment, id)
    }
}