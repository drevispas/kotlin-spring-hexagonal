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

fun AccountJpaEntity.toDomainEntity() = Account(
    Account.AccountId(accountNumber, id ?: -1L),
    accountName,
    Money(balanceAmount)
)
