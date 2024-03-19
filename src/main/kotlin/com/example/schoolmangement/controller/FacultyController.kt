package com.example.schoolmangement.controller


import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.model.Faculty
import com.example.schoolmangement.service.FacultyService
import com.example.schoolmangement.util.contants.Constant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${Constant.MAIN_URL}faculty")
class FacultyController(private val facultyService: FacultyService) {
    @GetMapping
    fun index(): ObjectResponse<List<Faculty>> {
        return facultyService.index()
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): ObjectResponse<Faculty> {
        return facultyService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): MessageResponse {
        return facultyService.deleteById(id)
    }

    @PostMapping
    fun save(@RequestBody newFaculty: Faculty): MessageResponse {
        return facultyService.save(newFaculty)
    }
    @PutMapping("{id}")
    fun updateById(@RequestBody updatedFaculty: Faculty ,@PathVariable("id") id: Long ):MessageResponse{
        return facultyService.updateById(updatedFaculty , id)
    }
}