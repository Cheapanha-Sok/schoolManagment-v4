package com.example.schoolmangement.repository

import com.example.schoolmangement.model.Account
import com.example.schoolmangement.model.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
@Repository
interface RefreshTokenRepository : JpaRepository<RefreshToken , Long> {
    fun findByToken(token :String) :Optional<RefreshToken>
    fun findByAccount(account :Account) :Optional<RefreshToken>
}