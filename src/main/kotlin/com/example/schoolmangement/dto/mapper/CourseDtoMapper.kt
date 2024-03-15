package com.example.schoolmangement.dto.mapper

import com.example.schoolmangement.dto.CourseDto
import com.example.schoolmangement.model.Course
import org.springframework.stereotype.Service
import java.util.function.Function

@Service
class CourseDtoMapper : Function<Course, CourseDto> {
    override fun apply(course: Course): CourseDto {
        return CourseDto(
            course.id!!,
            course.name!!,
            course.credit!!,
            course.type!!,
            course.departments!!.map { it.name!!}
        )
    }
}