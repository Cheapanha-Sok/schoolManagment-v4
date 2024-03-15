package com.example.schoolmangement.repository


import com.example.schoolmangement.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository :JpaRepository<Student,Long>