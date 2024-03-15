package com.example.schoolmangement.dto.mapper

import com.example.schoolmangement.dto.StudentDto
import com.example.schoolmangement.model.Student
import org.springframework.stereotype.Service
import java.util.function.Function

@Service
class StudentDtoMapper : Function<Student, StudentDto> {
    override fun apply(student: Student): StudentDto {
        return StudentDto(
            id = student.id!!,
            name = student.name!!,
            gender = student.gender!!,
            phoneNumber = student.phoneNumber!!,
            address = student.address!!,
            generation = student.generation!!,
            year = student.year!!,
            degree = student.degree!!,
//            age = student.age!!,
            department = student.departments!!.map { it.name!! },
            account = student.account!!.username!!
        )
    }
}
