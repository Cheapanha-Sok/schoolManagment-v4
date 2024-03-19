package com.example.schoolmangement.controller


import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.AccountDto
import com.example.schoolmangement.model.Account
import com.example.schoolmangement.service.AccountService
import com.example.schoolmangement.util.contants.Constant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${Constant.MAIN_URL}account")
class AccountController(private val accountService: AccountService) {
    @GetMapping
    fun index(
        @RequestParam(required = false) name: String?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PageResponse<AccountDto?>{
        return accountService.index(name, page , size)
    }

    @GetMapping("{id}")
    fun show(
        @PathVariable("id") id: Long,
    ): ObjectResponse<AccountDto> {
        return accountService.show(id)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable("id") id: Long): MessageResponse {
        return accountService.deleteById(id)
    }

    @PostMapping("student/{student_id}/{role_id}")
    fun saveStudent(
        @RequestBody newAccount: Account,
        @PathVariable("role_id") roleId: Long,
        @PathVariable("student_id") studentId: Long
    ): MessageResponse {
        return accountService.saveStudent(newAccount, roleId, studentId)
    }

    @PostMapping("teacher/{teacher_id}/{role_id}")
    fun saveTeacher(
        @RequestBody newAccount: Account,
        @PathVariable("role_id") roleId: Long,
        @PathVariable("teacher_id") teacherId: Long
    ): MessageResponse {
        return accountService.saveTeacher(newAccount, roleId, teacherId)
    }

    @PutMapping("{id}")
    fun updateById(@RequestBody updatedAccount: AccountDto, @PathVariable("id") id: Long): MessageResponse {
        return accountService.updateById(updatedAccount, id)
    }
}