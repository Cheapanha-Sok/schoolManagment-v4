package com.example.schoolmangement.repository

import com.example.schoolmangement.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account,Long>{
    fun findByName(name :String) : Optional<Account>
}