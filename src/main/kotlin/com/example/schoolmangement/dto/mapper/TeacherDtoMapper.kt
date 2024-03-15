package com.example.schoolmangement.dto.mapper

import com.example.schoolmangement.dto.TeacherDto
import com.example.schoolmangement.model.Teacher
import org.springframework.stereotype.Service
import java.util.function.Function

@Service
class TeacherDtoMapper : Function<Teacher , TeacherDto> {
    override fun apply(teacher: Teacher): TeacherDto {
        return TeacherDto(
            teacher.id!!,
            teacher.name!!,
            teacher.gender!!,
            teacher.address!!,
            teacher.phoneNumber!!,
            teacher.courses!!.map { it.name!! },
            teacher.account?.roles!!.map { it.authority!! },
        )
    }
}