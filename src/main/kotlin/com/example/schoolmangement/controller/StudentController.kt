package com.example.schoolmangement.controller

import com.example.schoolmangement.dto.StudentDto
import com.example.schoolmangement.model.Student
import com.example.schoolmangement.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/student")
class StudentController(private val studentService: StudentService) {

    @GetMapping
    fun index(): Iterable<StudentDto> {
        return studentService.index()
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): StudentDto {
        return studentService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<HttpStatus> {
        return studentService.deleteById(id)
    }

    @PostMapping("{department_id}")
    fun save(@RequestBody student: Student, @PathVariable("department_id") id: Long): ResponseEntity<HttpStatus> {
        return studentService.save(student, id)
    }

    @PutMapping("{department_id}")
    fun updateById(
        @RequestBody updatedStudent: StudentDto,
        @PathVariable("department_id") id: Long
    ): StudentDto {
        return studentService.updateById(updatedStudent, id)
    }
}