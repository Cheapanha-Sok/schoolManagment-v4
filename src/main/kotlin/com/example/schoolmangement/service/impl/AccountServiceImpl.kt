package com.example.schoolmangement.service.impl

import com.example.schoolmangement.base.reponse.MessageResponse
import com.example.schoolmangement.base.reponse.ObjectResponse
import com.example.schoolmangement.base.reponse.PageResponse
import com.example.schoolmangement.dto.AccountDto
import com.example.schoolmangement.dto.mapper.AccountDtoMapper
import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Account
import com.example.schoolmangement.model.Role
import com.example.schoolmangement.repository.AccountRepository
import com.example.schoolmangement.repository.RoleRepository
import com.example.schoolmangement.repository.StudentRepository
import com.example.schoolmangement.repository.TeacherRepository
import com.example.schoolmangement.service.AccountService
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val roleRepository: RoleRepository,
    private val teacherRepository: TeacherRepository,
    private val studentRepository: StudentRepository,
    private val passwordEncoder: PasswordEncoder,
    private val accountDtoMapper: AccountDtoMapper
) : AccountService {
    override fun show(id: Long): ObjectResponse<AccountDto> {
        val account = accountRepository.findById(id)?.orElseThrow { NotFoundException("Account not found with id $id") }
        return ObjectResponse(accountDtoMapper.apply(account!!))
    }

    override fun index(name :String ? , page :Int , size :Int): PageResponse<AccountDto?> {
        val account = accountRepository.findAll({root , cq , cb ->
            val predicates = ArrayList<Predicate>()
            name?.let {
                predicates.add(cb.like(cb.lower(root.get("name")),"%${it.lowercase()}%"))
            }
            cq.orderBy(cb.desc(root.get<Long>("id")))
            cb.and(*predicates.toTypedArray())
        },PageRequest.of(page,size))
        return PageResponse(account.totalElements , account.content.map { accountDtoMapper.apply(it) })
    }

    override fun deleteById(id: Long): MessageResponse {
        val account = accountRepository.findById(id)?.orElseThrow { NotFoundException("Account not found with id $id") }
        accountRepository.delete(account!!).let { return MessageResponse() }
    }

    override fun saveTeacher(newAccount: Account, roleId: Long, teacherId: Long): MessageResponse {
        val teacher = teacherRepository.findById(teacherId)
            ?.orElseThrow { NotFoundException("Teacher not found with id $teacherId") }
        val role = findRoleById(id = roleId)
        newAccount.roles = mutableListOf(role!!)
        newAccount.setPassword(passwordEncoder.encode(newAccount.password))
        val savedAccount = accountRepository.save(newAccount)
        teacher!!.account = savedAccount
        teacherRepository.save(teacher)
        return MessageResponse()
    }

    private fun findRoleById(id:Long) : Role?{
        return roleRepository.findById(id)?.orElseThrow{ NotFoundException("Role not found with id $id") }
    }

    override fun saveStudent(newAccount: Account, roleId: Long, studentId: Long): MessageResponse {
        val student = studentRepository.findById(studentId)
            ?.orElseThrow { NotFoundException("Student not found with id $studentId") }
        val role = findRoleById(id = roleId)
        newAccount.roles = mutableListOf(role!!)
        newAccount.setPassword(passwordEncoder.encode(newAccount.password))
        val savedAccount = accountRepository.save(newAccount)
        student!!.account = savedAccount
        studentRepository.save(student)
        return MessageResponse()
    }

    override fun updateById(updatedAccount: AccountDto, id: Long): MessageResponse {
        TODO("Not yet implemented")
    }
}