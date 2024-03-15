package com.example.schoolmangement.dto.mapper

import com.example.schoolmangement.dto.AccountDto
import com.example.schoolmangement.model.Account
import org.springframework.stereotype.Service
import java.util.function.Function

@Service
class AccountDtoMapper : Function<Account, AccountDto> {
    override fun apply(account: Account): AccountDto {
        return AccountDto(
            id = account.id!!,
            name = account.username!!,
            role = account.roles!!.map { it.authority!! }
        )
    }
}
