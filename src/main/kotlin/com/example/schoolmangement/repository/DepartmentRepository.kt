package com.example.schoolmangement.repository

import com.example.schoolmangement.model.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department ,Long>