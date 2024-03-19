package com.example.schoolmangement.service

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.AccountDto
import com.example.schoolmangement.model.Account
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface AccountService {
    fun index(name:String? , page :Int , size :Int): PageResponse<AccountDto?>
    fun show(id: Long): ObjectResponse<AccountDto>
    fun deleteById(id: Long): MessageResponse
    fun saveTeacher(newAccount: Account, roleId: Long, teacherId: Long): MessageResponse
    fun saveStudent(newAccount: Account, roleId: Long, studentId: Long): MessageResponse
    fun updateById(updatedAccount: AccountDto, id: Long): MessageResponse
}