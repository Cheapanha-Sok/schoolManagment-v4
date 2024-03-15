package com.example.schoolmangement.service.impl

import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Faculty
import com.example.schoolmangement.repository.FacultyRepository
import com.example.schoolmangement.service.FacultyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class FacultyServiceImpl(private val facultyRepository: FacultyRepository) : FacultyService {
    override fun show(id: Long): Faculty{
        val faculty: Optional<Faculty> = facultyRepository.findById(id)
        if (faculty.isPresent) {
            return faculty.get()
        } else
            throw NotFoundException("Faculty with id $id not found")
    }

    override fun index(): Iterable<Faculty> {
        return facultyRepository.findAll()
    }

    override fun deleteById(id: Long): ResponseEntity<HttpStatus> {
        val isExist = facultyRepository.existsById(id)
        if (isExist) {
            facultyRepository.deleteById(id)
            return ResponseEntity.noContent().build()
        } else
            throw NotFoundException("Faculty with id $id not found")
    }

    override fun save(newFaculty: Faculty): ResponseEntity<HttpStatus> {
        facultyRepository.save(newFaculty)
        return ResponseEntity.ok().build()
    }

    override fun updateById(updatedFaculty: Faculty, id: Long): Faculty {
        val faculty : Optional<Faculty> = facultyRepository.findById(id)
        if (faculty.isPresent){
            val currentFaculty = faculty.get()
            if (updatedFaculty.name != null && updatedFaculty.name!!.isNotEmpty()){
                currentFaculty.name = updatedFaculty.name
            }
            if (updatedFaculty.deanName != null && updatedFaculty.deanName!!.isNotEmpty()){
                currentFaculty.deanName = updatedFaculty.deanName
            }
            if (updatedFaculty.officeNumber != null && updatedFaculty.officeNumber!!.isNotEmpty()){
                currentFaculty.officeNumber = updatedFaculty.officeNumber
            }
            val updated = facultyRepository.save(currentFaculty)
            return updated
        }else
            throw NotFoundException("Faculty with id $id not found")
    }
}