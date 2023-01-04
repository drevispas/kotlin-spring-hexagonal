package com.example.kotlinspringhexagonal

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataAccountRepository : JpaRepository<AccountJpaEntity, Long> {

    fun findFirstByAccountNumber(accountNumber: Long): AccountJpaEntity?
}
