package com.example.kotlinspringhexagonal

import com.example.kotlinspringhexagonal.application.port.out.CreateAccountPort
import com.example.kotlinspringhexagonal.application.port.out.LoadAccountPort
import com.example.kotlinspringhexagonal.application.port.out.UpdateAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import org.springframework.stereotype.Component

@Component
class AccountPersistenceAdapter(
    private val accountRepostory: AccountRepository
) : CreateAccountPort, UpdateAccountPort, LoadAccountPort {
    override fun create(accountNumber: Long, accountName: String, balanceAmount: Long): Account {
        val saved = accountRepostory.save(AccountJpaEntity(null, accountNumber, accountName, balanceAmount))
        return Account(
            Account.AccountId(saved.accountNumber, saved.id ?: -1L),
            saved.accountName,
            Money(saved.balanceAmount)
        )
    }

    override fun load(accountNumber: Long): Account {
        TODO("Not yet implemented")
    }

    override fun update(accountNumber: Long, accountName: String): Account {
        TODO("Not yet implemented")
    }

}
