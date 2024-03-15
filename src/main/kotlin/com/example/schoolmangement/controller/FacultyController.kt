package com.example.schoolmangement.controller


import com.example.schoolmangement.model.Faculty
import com.example.schoolmangement.service.FacultyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/faculty")
class FacultyController(private val facultyService: FacultyService) {
    @GetMapping
    fun index(): Iterable<Faculty> {
        return facultyService.index()
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): Faculty {
        return facultyService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<HttpStatus> {
        return facultyService.deleteById(id)
    }

    @PostMapping
    fun save(@RequestBody newFaculty: Faculty): ResponseEntity<HttpStatus> {
        return facultyService.save(newFaculty)
    }
    @PutMapping("{id}")
    fun updateById(@RequestBody updatedFaculty: Faculty ,@PathVariable("id") id: Long ):Faculty{
        return facultyService.updateById(updatedFaculty , id)
    }
}