package com.example.schoolmangement.dto.mapper

import com.example.schoolmangement.dto.DepartmentDto
import com.example.schoolmangement.model.Department
import org.springframework.stereotype.Service
import java.util.function.Function

@Service
class DepartmentDtoMapper : Function<Department, DepartmentDto> {
    override fun apply(department: Department): DepartmentDto {
        return DepartmentDto(
            department.name!!,
            department.headName!!,
            department.officeNumber!!,
            department.faculty!!.name!!,
        )
    }
}