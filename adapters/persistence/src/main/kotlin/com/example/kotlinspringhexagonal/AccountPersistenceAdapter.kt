package com.example.kotlinspringhexagonal

import com.example.kotlinspringhexagonal.application.port.out.CreateAccountPort
import com.example.kotlinspringhexagonal.application.port.out.LoadAccountPort
import com.example.kotlinspringhexagonal.application.port.out.UpdateAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import org.springframework.stereotype.Component

/**
 * Outgoing adapter는 application-core에서 선언한 outgoing port 인터페이스를 구현하는 클래스이다.
 * Usecase 별로 존재하는 복수개의 outgoing port가 1개의 persistence adapter에 대응되는 것이 보통이다.
 * DDD aggregate 하나 당 persistence adapter를 한 개 씩 생성한다.
 *
 * 파라미터는 Spring JPA repository 또는 개인적으로 선언한 인터페이스가 될 수 있다.
 * JpaRepository를 상속한 인퍼페이스인 경우 Spring이 자동으로 구현체를 주입해준다.
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
