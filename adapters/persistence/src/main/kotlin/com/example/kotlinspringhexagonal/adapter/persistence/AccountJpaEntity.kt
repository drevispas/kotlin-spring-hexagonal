package com.example.kotlinspringhexagonal.adapter.persistence

import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

/**
 * ORM을 이용할 때 DB 테이블에 대응하는 객체를 선언한다. 이 때 클래스 이름은 "~JpaEntity"로 끝나도록 짓는다.
 * 대부분의 경우 JPA entity 클래스와 비슷하게 생긴 domain entity 클래스가 domain 영역에 존재하게 된다.
 */
@Entity
@Table(name = "account")
class AccountJpaEntity(
    @Id
    @GeneratedValue
    val id: Long?,
    val accountNumber: Long,
    val accountName: String,
    val balanceAmount: Long = 0L
)

// 확장 함수 대신 object로 converter를 따로 만들 수도 있다.
fun AccountJpaEntity.toDomainEntity() = Account(
    Account.AccountId(accountNumber, id ?: -1L),
    accountName,
    Money(balanceAmount)
)
