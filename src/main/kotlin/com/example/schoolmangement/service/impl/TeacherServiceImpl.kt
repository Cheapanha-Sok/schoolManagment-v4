package com.example.schoolmangement.service.impl

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.TeacherDto
import com.example.schoolmangement.dto.mapper.TeacherDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Course
import com.example.schoolmangement.model.Teacher
import com.example.schoolmangement.repository.CourseRepository
import com.example.schoolmangement.repository.TeacherRepository
import com.example.schoolmangement.service.TeacherService
import jakarta.persistence.criteria.Predicate
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class TeacherServiceImpl(
    private val teacherRepository: TeacherRepository,
    private val courseRepository: CourseRepository,
    private val teacherDtoMapper: TeacherDtoMapper
) : TeacherService {
    override fun show(id: Long): ObjectResponse<TeacherDto> {
        val teacher: Teacher =
            teacherRepository.findById(id)!!.orElseThrow { NotFoundException("Teacher with id $id not found") }
        return ObjectResponse(teacherDtoMapper.apply(teacher))
    }

    override fun index(name :String ? , page :Int , size :Int): PageResponse<TeacherDto?> {
        val teachers = teacherRepository.findAll({root , cq , cb->
            val predicates = ArrayList<Predicate>()
            name?.let {
                predicates.add(cb.like(cb.lower(root.get("name")),"%${it.lowercase()}%"))
            }
            cq.orderBy(cb.desc(root.get<Long>("id")))
            cb.and(*predicates.toTypedArray())
        },PageRequest.of(page , size))

        return PageResponse(teachers.totalElements , teachers.content.map { teacherDtoMapper.apply(it) })
    }
    @Transactional
    override fun deleteById(id: Long): MessageResponse {
        val teacher =
            teacherRepository.findById(id)!!.orElseThrow { NotFoundException("Teacher with id $id not found") }
        teacherRepository.save(teacher).let { return MessageResponse() }
    }
    @Transactional
    override fun save(newTeacher: Teacher, id: Long): MessageResponse {
        val course: Course =
            courseRepository.findById(id)!!.orElseThrow { NotFoundException("Course with id $id not found") }
        newTeacher.courses = listOf(course)
        teacherRepository.save(newTeacher)
        return MessageResponse()
    }
    @Transactional
    override fun updateById(updatedTeacher: TeacherDto, id: Long): MessageResponse {
        val teacher: Teacher =
            teacherRepository.findById(id)!!.orElseThrow { NotFoundException("Teacher with id $id not found") }
        updatedTeacher.name?.let { teacher.name = updatedTeacher.name }
        updatedTeacher.gender?.let { teacher.gender = updatedTeacher.gender }
        updatedTeacher.address?.let { teacher.address = updatedTeacher.address }
        updatedTeacher.phoneNumber?.let { teacher.phoneNumber = updatedTeacher.phoneNumber }

        teacherRepository.save(teacher).let { return MessageResponse() }
    }
}