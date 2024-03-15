package com.example.schoolmangement.controller


import com.example.schoolmangement.dto.DepartmentDto
import com.example.schoolmangement.model.Department
import com.example.schoolmangement.service.DepartmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/department")
class DepartmentController(private val departmentService: DepartmentService) {
    @GetMapping
    fun index(): Iterable<DepartmentDto> {
        return departmentService.index()
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): DepartmentDto {
        return departmentService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<HttpStatus> {
        return departmentService.deleteById(id)
    }

    @PostMapping("{faculty_id}")
    fun save(@RequestBody newDepartment: Department, @PathVariable("faculty_id") id: Long): ResponseEntity<HttpStatus> {
        return departmentService.save(newDepartment, id)
    }
    @PutMapping("{id}")
    fun updateById(@RequestBody updatedDepartment: DepartmentDto, @PathVariable("id") id: Long) : DepartmentDto{
        return departmentService.updateById(updatedDepartment , id)
    }
}