package com.example.schoolmangement.service.impl

import com.example.schoolmangement.repository.DepartmentRepository
import com.example.schoolmangement.dto.CourseDto
import com.example.schoolmangement.dto.mapper.CourseDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Course
import com.example.schoolmangement.model.Department
import com.example.schoolmangement.repository.CourseRepository
import com.example.schoolmangement.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional
import kotlin.jvm.optionals.toList

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val departmentRepository: DepartmentRepository,
    private val courseDtoMapper: CourseDtoMapper
) : CourseService {
    override fun show(id: Long): CourseDto {
        val course: Optional<Course> = courseRepository.findById(id)
        if (course.isPresent) {
            return courseDtoMapper.apply(course.get())
        } else
            throw NotFoundException("Course with id $id not found")
    }

    override fun index(): Iterable<CourseDto> {
        return courseRepository.findAll().map { course -> courseDtoMapper.apply(course) }
    }

    override fun deleteById(id: Long): ResponseEntity<HttpStatus> {
        val isExist = courseRepository.existsById(id)
        return if (isExist) {
            courseRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else
            throw NotFoundException("Course with id $id not found")
    }

    override fun save(newCourse: Course, id: Long): ResponseEntity<HttpStatus> {
        val department: Optional<Department> = departmentRepository.findById(id)
        if (department.isPresent) {
            newCourse.departments = department.toList()
            courseRepository.save(newCourse)
            return ResponseEntity.ok().build()
        } else
            throw NotFoundException("Course with id $id not found")
    }

    override fun updateById(updatedCourse: CourseDto, id: Long): CourseDto {
        val course: Optional<Course> = courseRepository.findById(id)
        if (course.isPresent) {
            val currentCourse = course.get()
            if (updatedCourse.name.isNotEmpty()) {
                currentCourse.name = updatedCourse.name
            }
            currentCourse.credit = updatedCourse.credit
            if (updatedCourse.type.isNotEmpty()) {
                currentCourse.type = updatedCourse.type
            }
            val updated = courseRepository.save(currentCourse)
            return courseDtoMapper.apply(updated)
        } else
            throw NotFoundException("Course with id $id not found")
    }
}