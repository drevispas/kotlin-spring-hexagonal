package com.example.kotlinspringhexagonal

import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

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
