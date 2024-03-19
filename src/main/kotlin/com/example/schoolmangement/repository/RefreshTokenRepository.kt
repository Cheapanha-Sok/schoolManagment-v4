package com.example.schoolmangement.repository

import com.example.schoolmangement.base.repository.BaseRepository
import com.example.schoolmangement.model.Account
import com.example.schoolmangement.model.RefreshToken
import java.util.*
interface RefreshTokenRepository : BaseRepository<RefreshToken, Long> {
    fun findByToken(token :String) :Optional<RefreshToken>
    fun findByAccount(account :Account) :Optional<RefreshToken>
}