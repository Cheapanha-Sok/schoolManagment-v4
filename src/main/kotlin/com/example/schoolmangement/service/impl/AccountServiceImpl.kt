package com.example.schoolmangement.service.impl

import com.example.schoolmangement.dto.AccountDto
import com.example.schoolmangement.dto.mapper.AccountDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Account
import com.example.schoolmangement.model.Role
import com.example.schoolmangement.model.Student
import com.example.schoolmangement.model.Teacher
import com.example.schoolmangement.repository.AccountRepository
import com.example.schoolmangement.repository.RoleRepository
import com.example.schoolmangement.repository.StudentRepository
import com.example.schoolmangement.repository.TeacherRepository
import com.example.schoolmangement.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val roleRepository: RoleRepository,
    private val teacherRepository: TeacherRepository,
    private val studentRepository: StudentRepository,
    private val passwordEncoder: PasswordEncoder,
    private val accountDtoMapper: AccountDtoMapper
) : AccountService {
    override fun show(id: Long): AccountDto {
        val account: Optional<Account> = accountRepository.findById(id)
        if (account.isPresent) {
            return accountDtoMapper.apply(account.get())
        } else
            throw NotFoundException("Account with id $id not found")
    }

    override fun index(): Iterable<AccountDto> {
        val accounts = accountRepository.findAll()
        return accounts.map { account ->
            accountDtoMapper.apply(account)
        }
    }

    override fun deleteById(id: Long): ResponseEntity<HttpStatus> {
        val isExist = accountRepository.existsById(id)
        if (isExist) {
            accountRepository.deleteById(id)
            return ResponseEntity.noContent().build()
        } else
            throw NotFoundException("Account with id $id not found")
    }

    override fun saveTeacher(newAccount: Account, roleId: Long, teacherId: Long): ResponseEntity<HttpStatus> {
        val teacher: Optional<Teacher> = teacherRepository.findById(teacherId)
        val role: Optional<Role> = roleRepository.findById(roleId)
        if (teacher.isPresent) {
            if (role.isPresent) {
                newAccount.roles = mutableListOf(role.get())
                newAccount.setPassword(passwordEncoder.encode(newAccount.password))
                val savedAccount = accountRepository.save(newAccount)
                val teacherInstance = teacher.get()
                teacherInstance.account = savedAccount
                teacherRepository.save(teacherInstance)
                return ResponseEntity.ok().build()
            } else
                throw NotFoundException("Role with $roleId not found")
        } else
            throw NotFoundException("Teacher with $teacherId not found")
    }


    override fun saveStudent(newAccount: Account, roleId: Long, studentId: Long): ResponseEntity<HttpStatus> {
        val student: Optional<Student> = studentRepository.findById(studentId)
        val role: Optional<Role> = roleRepository.findById(roleId)
        if (student.isPresent) {
            if (role.isPresent) {
                newAccount.roles = mutableListOf(role.get())
                newAccount.setPassword(passwordEncoder.encode(newAccount.password))
                val savedAccount = accountRepository.save(newAccount)
                val studentInstance = student.get()
                studentInstance.account = savedAccount
                studentRepository.save(studentInstance)
                return ResponseEntity.ok().build()
            } else {
                throw NotFoundException("Role with $roleId not found")
            }
        } else {
            throw NotFoundException("Student with $studentId not found")
        }
    }

    override fun updateById(updatedAccount: AccountDto, id: Long): AccountDto {
        TODO("Not yet implemented")
    }
}