package com.example.schoolmangement.service.impl

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.DepartmentDto
import com.example.schoolmangement.dto.mapper.DepartmentDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Department
import com.example.schoolmangement.repository.DepartmentRepository
import com.example.schoolmangement.repository.FacultyRepository
import com.example.schoolmangement.service.DepartmentService
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class DepartmentServiceImpl(
    private val departmentRepository: DepartmentRepository, private val facultyRepository: FacultyRepository,
    private val departmentDtoMapper: DepartmentDtoMapper
) :
    DepartmentService {
    override fun show(id: Long): ObjectResponse<DepartmentDto> {
        val department: Department =
            departmentRepository.findById(id)!!.orElseThrow { NotFoundException("Department not found with id $id") }
        return ObjectResponse(departmentDtoMapper.apply(department))
    }

    override fun index(name :String? , page :Int , size :Int): PageResponse<DepartmentDto?>{
        val departments = departmentRepository.findAll({root , cq , cb ->
            val predicates = ArrayList<Predicate>()
            name?.let { predicates.add(cb.like(cb.lower(root.get("name")),"%${it.lowercase()}%"))
            }
            cq.orderBy(cb.desc(root.get<Long>("id")))
            cb.and(*predicates.toTypedArray())
        },PageRequest.of(page , size))
        return PageResponse(departments.totalElements , departments.content.map { departmentDtoMapper.apply(it) })
    }

    override fun deleteById(id: Long): MessageResponse {
        val department =
            departmentRepository.findById(id)?.orElseThrow { NotFoundException("Department not found with id $id") }
        departmentRepository.delete(department!!).let { return MessageResponse() }
    }

    override fun save(newDepartment: Department, id: Long): MessageResponse {
        val faculty =
            facultyRepository.findById(id)!!.orElseThrow { throw NotFoundException("Department with id $id not found") }
        newDepartment.faculty = faculty
        departmentRepository.save(newDepartment)
        return MessageResponse()
    }

    override fun updateById(updatedDepartment: DepartmentDto, id: Long): MessageResponse {
        val department: Department = departmentRepository.findById(id)!!
            .orElseThrow { throw NotFoundException("Department with id $id not found") }
        updatedDepartment.name?.let { department.name = updatedDepartment.name }
        updatedDepartment.headName?.let { department.headName = updatedDepartment.headName }
        updatedDepartment.office?.let { department.officeNumber = updatedDepartment.office }

        departmentRepository.save(department).let { return MessageResponse() }


    }
}