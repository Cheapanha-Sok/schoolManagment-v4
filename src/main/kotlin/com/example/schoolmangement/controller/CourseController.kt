package com.example.schoolmangement.controller

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.CourseDto
import com.example.schoolmangement.model.Course
import com.example.schoolmangement.service.CourseService
import com.example.schoolmangement.util.contants.Constant
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("${Constant.MAIN_URL}course")
class CourseController(private val courseService: CourseService) {
    @GetMapping
    fun index(
        @RequestParam(required = false) name: String? = null,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PageResponse<CourseDto?> {
        return courseService.index(name , page , size)
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): ObjectResponse<CourseDto> {
        return courseService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): MessageResponse {
        return courseService.deleteById(id)
    }

    @PostMapping("{department_id}")
    fun save(@RequestBody newCourse: Course, @PathVariable("department_id") id: Long): MessageResponse {
        return courseService.save(newCourse, id)
    }

    @PutMapping("{id}")
    fun updateById(@RequestBody updatedCourse: CourseDto, @PathVariable("id") id: Long): MessageResponse {
        return courseService.updateById(updatedCourse, id)
    }
}