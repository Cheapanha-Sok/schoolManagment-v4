package com.example.schoolmangement.controller

import com.example.schoolmangement.dto.CourseDto
import com.example.schoolmangement.model.Course
import com.example.schoolmangement.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/course")
class CourseController(private val courseService: CourseService) {
    @GetMapping
    fun index(): Iterable<CourseDto> {
        return courseService.index()
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): CourseDto {
        return courseService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<HttpStatus> {
        return courseService.deleteById(id)
    }

    @PostMapping("{department_id}")
    fun save(@RequestBody newCourse: Course , @PathVariable("department_id") id:Long): ResponseEntity<HttpStatus> {
        return courseService.save(newCourse , id)
    }
    @PutMapping("{id}")
    fun updateById(@RequestBody updatedCourse: CourseDto, @PathVariable("id") id: Long ):CourseDto{
        return courseService.updateById(updatedCourse , id)
    }
}