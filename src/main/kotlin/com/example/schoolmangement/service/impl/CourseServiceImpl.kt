package com.example.schoolmangement.service.impl

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.repository.DepartmentRepository
import com.example.schoolmangement.dto.CourseDto
import com.example.schoolmangement.dto.mapper.CourseDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Course
import com.example.schoolmangement.model.Department
import com.example.schoolmangement.repository.CourseRepository
import com.example.schoolmangement.service.CourseService
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val departmentRepository: DepartmentRepository,
    private val courseDtoMapper: CourseDtoMapper
) : CourseService {
    override fun show(id: Long): ObjectResponse<CourseDto> {
        val course = courseRepository.findById(id)?.orElseThrow { NotFoundException("Course not found with id $id") }
        return ObjectResponse(courseDtoMapper.apply(course!!))
    }

    override fun index(name :String?, page :Int, size :Int): PageResponse<CourseDto?> {
        val courses = courseRepository.findAll({root , cq , cb ->
            val predicates = ArrayList<Predicate>()
            name?.let {
                predicates.add(cb.like(cb.lower(root.get("name")), "%${it.lowercase()}%"))
            }
            cq.orderBy(cb.desc(root.get<Long>("id")))
            cb.and(*predicates.toTypedArray())
        },PageRequest.of(page , size))
        return PageResponse(courses.totalElements , courses.content.map { courseDtoMapper.apply(it) })
    }
    override fun deleteById(id: Long): MessageResponse {
        val course = courseRepository.findById(id)?.orElseThrow { NotFoundException("Course not found with id $id") }
        courseRepository.delete(course!!).let { return MessageResponse() }
    }

    override fun save(newCourse: Course, id: Long): MessageResponse {
        val department : Department = departmentRepository.findById(id)!!.orElseThrow {NotFoundException("Department not found with id $id")}
        newCourse.departments = listOf(department)
        courseRepository.save(newCourse).let { return MessageResponse() }
    }

    override fun updateById(updatedCourse: CourseDto, id: Long): MessageResponse {
        val course: Course =
            courseRepository.findById(id)!!.orElseThrow { NotFoundException("Course not found with id $id") }
        updatedCourse.credit?.let { course.credit = updatedCourse.credit }
        updatedCourse.name?.let { course.name = updatedCourse.name }
        updatedCourse.type?.let { course.type = updatedCourse.type }
        courseRepository.save(course).let { return MessageResponse() }
    }
}