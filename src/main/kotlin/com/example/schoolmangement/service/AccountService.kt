package com.example.schoolmangement.service

import com.example.schoolmangement.dto.AccountDto
import com.example.schoolmangement.model.Account
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface AccountService {
    fun index(): Iterable<AccountDto>
    fun show(id: Long): AccountDto
    fun deleteById(id: Long): ResponseEntity<HttpStatus>
    fun saveTeacher(newAccount: Account, roleId: Long, teacherId: Long): ResponseEntity<HttpStatus>
    fun saveStudent(newAccount: Account, roleId: Long, studentId: Long): ResponseEntity<HttpStatus>
    fun updateById(updatedAccount: AccountDto, id: Long): AccountDto
}