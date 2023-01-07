package com.example.kotlinspringhexagonal.adapter.persistence

import com.example.kotlinspringhexagonal.application.port.out.CreateAccountPort
import com.example.kotlinspringhexagonal.application.port.out.LoadAccountPort
import com.example.kotlinspringhexagonal.application.port.out.UpdateAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import org.springframework.stereotype.Component

/**
 * Outgoing adapter는 application-core에서 선언한 outgoing port 인터페이스를 구현하는 클래스이다.
 * Usecase 별로 존재하는 복수개의 outgoing port가 1개의 persistence adapter에 대응되는 것이 보통이다.
 * DDD aggregate 하나 당 persistence adapter를 한 개 씩 생성한다.
 *
 * 생성자 파라미터는 Spring JPA repository 또는 개인적으로 선언한 인터페이스가 될 수 있다.
 * JpaRepository를 상속한 인퍼페이스인 경우 Spring이 자동으로 구현체를 주입해준다.
 */
@Component
class AccountPersistenceAdapter(
    private val accountRepostory: SpringDataAccountRepository
) : CreateAccountPort, UpdateAccountPort, LoadAccountPort {

    /**
     * JPA 입장에서는 Jpa Entity가 입력 모델이다.
     * Usecase -> Persistence로 요청할 때는 별도의 command 객체를 생성하지 않고
     * Account에서 필요한 데이터만 파라미터로 추출하거나 Account 엔터티 객체를 통째로 넘긴다.
     */
    override fun create(account: Account): Account {
        val saved = accountRepostory.save(AccountJpaEntity.fromDomainEntity(account))
        /**
         * Persistence --> Usecase로의 출력 모델도 별도의 출력 모델을 정의하지 않고
         * Account 엔터티 모델을 그대로 사용한다.
         * 즉, Web <-> Usecase 간에 완전 매핑 전략을, Usecase <-> Persistence 간에는 two-way 매핑 전략을 사용한다.
         */
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
