package com.example.schoolmangement.service.impl

import com.example.schoolmangement.dto.DepartmentDto
import com.example.schoolmangement.dto.mapper.DepartmentDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Department
import com.example.schoolmangement.repository.DepartmentRepository
import com.example.schoolmangement.repository.FacultyRepository
import com.example.schoolmangement.service.DepartmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class DepartmentServiceImpl(
    private val departmentRepository: DepartmentRepository, private val facultyRepository: FacultyRepository,
    private val departmentDtoMapper: DepartmentDtoMapper
) :
    DepartmentService {
    override fun show(id: Long): DepartmentDto {
        val department = departmentRepository.findById(id)
        if (department.isPresent) {
            return departmentDtoMapper.apply(department.get())
        } else
            throw NotFoundException("Department with id $id not found")
    }

    override fun index(): Iterable<DepartmentDto> {
        return departmentRepository.findAll().map { department -> departmentDtoMapper.apply(department) }
    }

    override fun deleteById(id: Long): ResponseEntity<HttpStatus> {
        val isExist = departmentRepository.existsById(id)
        if (isExist) {
            departmentRepository.deleteById(id)
            return ResponseEntity.noContent().build()
        } else
            throw NotFoundException("Department with id $id not found")
    }

    override fun save(newDepartment: Department, id: Long): ResponseEntity<HttpStatus> {
        val faculty = facultyRepository.findById(id)
        if (faculty.isPresent) {
            newDepartment.faculty = faculty.get()
            departmentRepository.save(newDepartment)
            return ResponseEntity.ok().build()
        } else
            throw NotFoundException("Department with id $id not found")

    }

    override fun updateById(updatedDepartment: DepartmentDto, id: Long): DepartmentDto {
        val department: Optional<Department> = departmentRepository.findById(id)
        if (department.isPresent) {
            val currentDepartment = department.get()
            if (updatedDepartment.name.isNotEmpty()) {
                currentDepartment.name = updatedDepartment.name
            }
            if (updatedDepartment.headName.isNotEmpty()) {
                currentDepartment.headName = updatedDepartment.headName
            }
            if (updatedDepartment.office.isNotEmpty()) {
                currentDepartment.officeNumber = updatedDepartment.office
            }
            val updated = departmentRepository.save(currentDepartment)
            return departmentDtoMapper.apply(updated)
        } else
            throw NotFoundException("Department with id $id not found")
    }
}