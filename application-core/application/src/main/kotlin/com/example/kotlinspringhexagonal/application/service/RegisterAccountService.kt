package com.example.kotlinspringhexagonal.application.service

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import com.example.kotlinspringhexagonal.application.port.out.CreateAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Incoming port Usecase의 구현체이다.
 * 기본적으로 rich domain model을 선호하여 비지니스 규칙은 usecase 구현체가 아닌 domain entity 안에서 구현하도록 한다.
 * Domain entity 안에서 비지니스 규칙을 검사하기 어려운 경우에는 여기 usecase 구현체에서 진행한다.
 */
@Service
/**
 * @Transactional은 web이나 persistence가 아니라 service 계층에서 사용하여야 한다.
 * Service 계층만이 transaction boudary를 결정할 수 있다.
 * 트랜잭션이 필요한 클래스에는 readOnly = true를 붙이고 DML이 발생하는 method에만 @Transactional을 추가로 붙인다.
 * - https://vladmihalcea.com/spring-transactional-annotation/
 */
@Transactional(readOnly = true)
class RegisterAccountService(
    private val createAccountPort: CreateAccountPort
) : RegisterAccountUseCase {

    @Transactional
    override fun register(command: RegisterAccountUseCase.Command): RegisterAccountUseCase.Result {

        /* Account 엔터티 생성자가 비지니스 규칙을 검사하도록 하고 있다. */
        val account =
            Account(Account.AccountId(command.accountNumber), command.accountName, Money(command.balanceAmount))
        val savedAccount = createAccountPort.create(account)
        return RegisterAccountUseCase.fromDomainEntity(savedAccount)
    }
}
