package com.example.schoolmangement.controller


import com.example.schoolmangement.dto.AccountDto
import com.example.schoolmangement.model.Account
import com.example.schoolmangement.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/account")
class AccountController(private val accountService: AccountService) {
    @GetMapping
    fun index(): Iterable<AccountDto> {
        return accountService.index()
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): AccountDto {
        return accountService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<HttpStatus> {
        return accountService.deleteById(id)
    }

    @PostMapping("student/{student_id}/{role_id}")
    fun saveStudent(@RequestBody newAccount: Account, @PathVariable("role_id") roleId: Long , @PathVariable("student_id") studentId :Long ): ResponseEntity<HttpStatus>{
        return accountService.saveStudent(newAccount, roleId , studentId)
    }
    @PostMapping("teacher/{teacher_id}/{role_id}")
    fun saveTeacher(@RequestBody newAccount: Account, @PathVariable("role_id") roleId: Long , @PathVariable("teacher_id") teacherId :Long ): ResponseEntity<HttpStatus> {
        return accountService.saveTeacher(newAccount, roleId , teacherId)
    }
    @PutMapping("{id}")
    fun updateById(@RequestBody updatedAccount: AccountDto, @PathVariable("id") id: Long ):AccountDto{
        return accountService.updateById(updatedAccount , id)
    }
}