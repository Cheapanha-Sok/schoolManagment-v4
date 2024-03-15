package com.example.schoolmangement.service.impl


import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Account
import com.example.schoolmangement.repository.AccountRepository
import com.example.schoolmangement.service.UserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserDetailsServiceImpl(private val accountRepository: AccountRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val account: Optional<Account> = accountRepository.findByName(username)
        if (account.isPresent){
            return account.get()
        }else
            throw NotFoundException("Username with $username not found")
    }
}