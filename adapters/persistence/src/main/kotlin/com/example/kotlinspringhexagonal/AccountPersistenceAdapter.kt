package com.example.kotlinspringhexagonal

import com.example.kotlinspringhexagonal.application.port.out.CreateAccountPort
import com.example.kotlinspringhexagonal.application.port.out.LoadAccountPort
import com.example.kotlinspringhexagonal.application.port.out.UpdateAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import org.springframework.stereotype.Component

/**
 * Outgoing adapter는 DDD aggregate 하나 당 한 개 씩 생성한다.
 */
@Component
class AccountPersistenceAdapter(
    private val accountRepostory: SpringDataAccountRepository
) : CreateAccountPort, UpdateAccountPort, LoadAccountPort {

    override fun create(accountNumber: Long, accountName: String, balanceAmount: Long): Account {
        val saved = accountRepostory.save(AccountJpaEntity(null, accountNumber, accountName, balanceAmount))
        return saved.toDomainEntity()
    }

    override fun load(accountNumber: Long): Account {
        val loaded = accountRepostory.findFirstByAccountNumber(accountNumber)
        loaded ?: throw NoSuchElementException("Account not found!")
        return loaded.toDomainEntity()
    }

    override fun update(accountNumber: Long, accountName: String): Account {
        TODO("Not yet implemented")
    }
}
