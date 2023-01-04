package com.example.kotlinspringhexagonal

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
