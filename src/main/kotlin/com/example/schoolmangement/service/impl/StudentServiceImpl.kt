package com.example.schoolmangement.service.impl

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.StudentDto
import com.example.schoolmangement.dto.mapper.StudentDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Department
import com.example.schoolmangement.model.Student
import com.example.schoolmangement.repository.DepartmentRepository
import com.example.schoolmangement.repository.StudentRepository
import com.example.schoolmangement.service.StudentService
import jakarta.persistence.criteria.Predicate
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
    private val departmentRepository: DepartmentRepository,
    private val studentDtoMapper: StudentDtoMapper
) : StudentService {
    override fun index(name :String? , page :Int , size :Int): PageResponse<StudentDto?> {
        val students = studentRepository.findAll({root , cq , cb->
            val predicates = ArrayList<Predicate>()
            name?.let { predicates.add(cb.like(cb.lower(root.get("name")),"%${it.lowercase()}%")) }
            cq.orderBy(cb.desc(root.get<Long>("id")))
            cb.and(*predicates.toTypedArray())
        },PageRequest.of(page, size))
        return PageResponse(students.totalElements , students.content.map { studentDtoMapper.apply(it) })
    }

    override fun show(id: Long): ObjectResponse<StudentDto> {
        val student: Student =
            studentRepository.findById(id)!!.orElseThrow { NotFoundException("Student with $id not found") }
        return ObjectResponse(studentDtoMapper.apply(student))
    }
    @Transactional
    override fun deleteById(id: Long): MessageResponse {
        val student = studentRepository.findById(id)!!.orElseThrow { NotFoundException("Student with $id not found") }
        studentRepository.delete(student).let { return MessageResponse() }
    }
    @Transactional
    override fun save(newStudent: Student, id: Long): MessageResponse {
        val department: Department =
            departmentRepository.findById(id)!!.orElseThrow { NotFoundException("Department with id $id not found") }
        newStudent.departments = listOf(department)
        studentRepository.save(newStudent)
        return MessageResponse()

    }
    @Transactional
    override fun updateById(updatedStudent: StudentDto, id: Long): MessageResponse {
        val student: Student =
            studentRepository.findById(id)!!.orElseThrow { NotFoundException("Teacher with id $id not found") }
        updatedStudent.name?.let { student.name = updatedStudent.name }
        updatedStudent.gender?.let { student.name = updatedStudent.name }
        updatedStudent.address?.let { student.address = updatedStudent.address }
        updatedStudent.generation?.let { student.generation = updatedStudent.generation }
        updatedStudent.degree?.let { student.degree = updatedStudent.degree }
        updatedStudent.year?.let { student.year = updatedStudent.year }
        updatedStudent.generation?.let { student.generation = updatedStudent.generation }

        studentRepository.save(student)
        return MessageResponse()
    }
}