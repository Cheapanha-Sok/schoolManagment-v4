package com.example.schoolmangement.service.impl

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Faculty
import com.example.schoolmangement.repository.FacultyRepository
import com.example.schoolmangement.service.FacultyService
import org.springframework.stereotype.Service

@Service
class FacultyServiceImpl(private val facultyRepository: FacultyRepository) : FacultyService {
    override fun show(id: Long): ObjectResponse<Faculty> {
        val faculty: Faculty =
            facultyRepository.findById(id)!!.orElseThrow { NotFoundException("Faculty with id $id not found") }
        return ObjectResponse(faculty)
    }

    override fun index(): ObjectResponse<List<Faculty>> {
        return ObjectResponse(facultyRepository.findAll())
    }

    override fun deleteById(id: Long): MessageResponse {
        val faculty =
            facultyRepository.findById(id)!!.orElseThrow { NotFoundException("Faculty with id $id not found") }
        facultyRepository.delete(faculty).let { return MessageResponse() }
    }

    override fun save(newFaculty: Faculty): MessageResponse {
        facultyRepository.save(newFaculty)
        return MessageResponse()
    }

    override fun updateById(updatedFaculty: Faculty, id: Long): MessageResponse {
        val faculty: Faculty =
            facultyRepository.findById(id)!!.orElseThrow { NotFoundException("Faculty with id $id not found") }
        updatedFaculty.name?.let { faculty.name = updatedFaculty.name }
        updatedFaculty.deanName?.let { faculty.deanName = updatedFaculty.deanName }
        updatedFaculty.officeNumber?.let { faculty.officeNumber = updatedFaculty.officeNumber }
        facultyRepository.save(faculty)
        return MessageResponse()
    }
}