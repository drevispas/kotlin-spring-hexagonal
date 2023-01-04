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
        val loaded = accountRepostory.findFirstByAccountNumber(accountNumber)
        loaded ?: throw NoSuchElementException("Account not found!")
        return Account(
            Account.AccountId(loaded.accountNumber, loaded.id ?: -1),
            loaded.accountName,
            Money(loaded.balanceAmount)
        )
    }

    override fun update(accountNumber: Long, accountName: String): Account {
        TODO("Not yet implemented")
    }

}
