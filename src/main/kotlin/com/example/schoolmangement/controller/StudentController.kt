package com.example.schoolmangement.controller

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.StudentDto
import com.example.schoolmangement.model.Student
import com.example.schoolmangement.service.StudentService
import com.example.schoolmangement.util.contants.Constant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${Constant.MAIN_URL}student")
class StudentController(private val studentService: StudentService) {

    @GetMapping
    fun index(
        @RequestParam(required = false) name: String?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PageResponse<StudentDto?> {
        return studentService.index(name , page, size)
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): ObjectResponse<StudentDto> {
        return studentService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): MessageResponse {
        return studentService.deleteById(id)
    }

    @PostMapping("{department_id}")
    fun save(@RequestBody student: Student, @PathVariable("department_id") id: Long): MessageResponse {
        return studentService.save(student, id)
    }

    @PutMapping("{department_id}")
    fun updateById(
        @RequestBody updatedStudent: StudentDto,
        @PathVariable("department_id") id: Long
    ): MessageResponse {
        return studentService.updateById(updatedStudent, id)
    }
}