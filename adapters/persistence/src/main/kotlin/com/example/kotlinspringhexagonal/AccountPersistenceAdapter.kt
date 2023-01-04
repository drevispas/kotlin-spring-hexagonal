package com.example.kotlinspringhexagonal

import com.example.kotlinspringhexagonal.application.port.out.CreateAccountPort
import com.example.kotlinspringhexagonal.application.port.out.LoadAccountPort
import com.example.kotlinspringhexagonal.application.port.out.UpdateAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import org.springframework.stereotype.Component

@Component
class AccountPersistenceAdapter(
//    private val accountRepostory: AccountRepository
) : CreateAccountPort, UpdateAccountPort, LoadAccountPort {
    override fun create(accountNumber: Long, accountName: String, balance: Money): Account {
        TODO("Not yet implemented")
    }

    override fun load(accountNumber: Long): Account {
        TODO("Not yet implemented")
    }

    override fun update(accountNumber: Long, accountName: String): Account {
        TODO("Not yet implemented")
    }

}
