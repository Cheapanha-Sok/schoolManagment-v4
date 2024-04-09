package com.example.schoolmangement.service.impl


import com.example.schoolmangement.exception.NotFoundException
import com.example.schoolmangement.model.Account
import com.example.schoolmangement.repository.AccountRepository
import com.example.schoolmangement.service.UserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserDetailsServiceImpl(private val accountRepository: AccountRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        try {
            return accountRepository.findByName(username).get()
        }catch (e: UsernameNotFoundException){
            throw UsernameNotFoundException("User $username not found")
        }
    }
}