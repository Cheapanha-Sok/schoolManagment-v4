package com.example.schoolmangement.controller

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.TeacherDto
import com.example.schoolmangement.model.Teacher
import com.example.schoolmangement.service.TeacherService
import com.example.schoolmangement.util.contants.Constant
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("${Constant.MAIN_URL}teacher")
class TeacherController(private val teacherService: TeacherService) {
    @GetMapping
    fun index(
        @RequestParam(required = false) name: String?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PageResponse<TeacherDto?>{
        return teacherService.index(name , page , size)
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): ObjectResponse<TeacherDto> {
        return teacherService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): MessageResponse {
        return teacherService.deleteById(id)
    }

    @PostMapping("{course_id}")
    fun save(@RequestBody newTeacher: Teacher, @PathVariable("course_id") id: Long): MessageResponse {
        return teacherService.save(newTeacher, id)
    }

    @PutMapping("{id}")
    fun updateById(@RequestBody updatedTeacher: TeacherDto, @PathVariable("id") id: Long): MessageResponse {
        return teacherService.updateById(updatedTeacher, id)
    }

}