package com.example.schoolmangement.repository

import com.example.schoolmangement.base.repository.BaseRepository
import com.example.schoolmangement.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface AccountRepository : BaseRepository<Account,Long>{
    fun findByName(name :String) : Optional<Account>
}