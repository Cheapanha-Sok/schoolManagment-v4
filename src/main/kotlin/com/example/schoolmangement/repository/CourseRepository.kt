package com.example.schoolmangement.repository

import com.example.schoolmangement.base.repository.BaseRepository
import com.example.schoolmangement.model.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


interface CourseRepository : BaseRepository<Course, Long>