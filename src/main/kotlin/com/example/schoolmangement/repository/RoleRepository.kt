package com.example.schoolmangement.repository

import com.example.schoolmangement.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface RoleRepository : JpaRepository<Role , Long>