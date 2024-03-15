package com.example.schoolmangement.controller

import com.example.schoolmangement.dto.TeacherDto
import com.example.schoolmangement.model.Teacher
import com.example.schoolmangement.service.TeacherService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/teacher")
class TeacherController(private val teacherService: TeacherService) {
    @GetMapping
    fun index(): Iterable<TeacherDto> {
        return teacherService.index()
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): TeacherDto{
        return teacherService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<HttpStatus> {
        return teacherService.deleteById(id)
    }

    @PostMapping("{course_id}")
    fun save(@RequestBody newTeacher: Teacher , @PathVariable("course_id") id:Long): ResponseEntity<HttpStatus> {
        return teacherService.save(newTeacher , id)
    }
    @PutMapping("{id}")
    fun updateById(@RequestBody updatedTeacher : TeacherDto , @PathVariable("id") id:Long): TeacherDto{
        return teacherService.updateById(updatedTeacher,id)
    }

}