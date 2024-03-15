package com.example.schoolmangement.service.impl

import com.example.schoolmangement.dto.TeacherDto
import com.example.schoolmangement.dto.mapper.TeacherDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Course
import com.example.schoolmangement.model.Teacher
import com.example.schoolmangement.repository.CourseRepository
import com.example.schoolmangement.repository.TeacherRepository
import com.example.schoolmangement.service.TeacherService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional
import kotlin.jvm.optionals.toList

@Service
class TeacherServiceImpl(
    private val teacherRepository: TeacherRepository,
    private val courseRepository: CourseRepository,
    private val teacherDtoMapper: TeacherDtoMapper
) : TeacherService {
    override fun show(id: Long): TeacherDto {
        val teacher: Optional<Teacher> = teacherRepository.findById(id)
        if (teacher.isPresent) {
            return teacherDtoMapper.apply(teacher.get())
        } else
            throw NotFoundException("Teacher with id $id not found")
    }

    override fun index(): Iterable<TeacherDto> {
        return teacherRepository.findAll().map { teacher -> teacherDtoMapper.apply(teacher) }
    }

    override fun deleteById(id: Long): ResponseEntity<HttpStatus> {
        val isExist = teacherRepository.existsById(id)
        if (isExist) {
            teacherRepository.deleteById(id)
            return ResponseEntity.ok().build()
        } else
            throw NotFoundException("Teacher with id $id not found")
    }

    override fun save(newTeacher: Teacher, id: Long): ResponseEntity<HttpStatus> {
        val course: Optional<Course> = courseRepository.findById(id)
        if (course.isPresent) {
            newTeacher.courses = course.toList()
            teacherRepository.save(newTeacher)
            return ResponseEntity.ok().build()
        } else
            throw NotFoundException("Teacher with id $id not found")
    }

    override fun updateById(updatedTeacher: TeacherDto, id: Long): TeacherDto {
        val teacher: Optional<Teacher> = teacherRepository.findById(id)
        if (teacher.isPresent) {
            val currentTeacher: Teacher = teacher.get()
            if (updatedTeacher.name.isNotEmpty()) {
                currentTeacher.name = updatedTeacher.name
            }
            if (updatedTeacher.gender.isNotEmpty()) {
                currentTeacher.gender = updatedTeacher.gender
            }
            if (updatedTeacher.address.isNotEmpty()) {
                currentTeacher.address = updatedTeacher.address
            }
            if (updatedTeacher.phoneNumber.isNotEmpty()) {
                currentTeacher.phoneNumber = updatedTeacher.phoneNumber
            }
            val updated: Teacher = teacherRepository.save(currentTeacher)
            return teacherDtoMapper.apply(updated)
        } else
            throw NotFoundException("Teacher with id $id not found")
    }
}