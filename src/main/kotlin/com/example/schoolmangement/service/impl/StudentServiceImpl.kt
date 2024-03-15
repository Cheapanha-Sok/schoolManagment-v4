package com.example.schoolmangement.service.impl

import com.example.schoolmangement.dto.StudentDto
import com.example.schoolmangement.dto.mapper.StudentDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Department
import com.example.schoolmangement.model.Student
import com.example.schoolmangement.repository.DepartmentRepository
import com.example.schoolmangement.repository.StudentRepository
import com.example.schoolmangement.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional
import kotlin.jvm.optionals.toList

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
    private val departmentRepository: DepartmentRepository,
    private val studentDtoMapper: StudentDtoMapper
) : StudentService {
    override fun index(): Iterable<StudentDto> {
        val students = studentRepository.findAll()
        return students.map { student ->
            studentDtoMapper.apply(student)
        }
    }

    override fun show(id: Long): StudentDto {
        val student: Optional<Student> = studentRepository.findById(id)
        if (student.isPresent) {
            return studentDtoMapper.apply(student.get())
        } else
            throw NotFoundException("Student with $id not found")
    }

    override fun deleteById(id: Long): ResponseEntity<HttpStatus> {
        val isExist = studentRepository.existsById(id)
        if (isExist) {
            studentRepository.deleteById(id)
            return ResponseEntity.ok().build()
        } else
            throw NotFoundException("Student with $id not found")
    }

    override fun save(newStudent: Student, id: Long): ResponseEntity<HttpStatus> {
        val department: Optional<Department> = departmentRepository.findById(id)
        if (department.isPresent) {
            newStudent.departments = department.toList()
            studentRepository.save(newStudent)
            return ResponseEntity.ok().build()
        } else
            throw NotFoundException("Department with id $id not found")

    }

    override fun updateById(updatedStudent: StudentDto, id: Long): StudentDto {
        val student: Optional<Student> = studentRepository.findById(id)
        if (student.isPresent) {
            val currentStudent: Student = student.get()
            if (currentStudent.name!!.isNotEmpty()) {
                currentStudent.name = updatedStudent.name
            }
            if (currentStudent.gender!!.isNotEmpty()) {
                currentStudent.gender = updatedStudent.gender
            }
            if (updatedStudent.address.isNotEmpty()) {
                currentStudent.address = updatedStudent.address
            }
            currentStudent.generation = updatedStudent.generation
            currentStudent.year = updatedStudent.year
            currentStudent.generation = updatedStudent.generation
            currentStudent.degree = updatedStudent.degree
            val updated: Student = studentRepository.save(currentStudent)
            return studentDtoMapper.apply(updated)
        } else
            throw NotFoundException("Teacher with id $id not found")
    }
}