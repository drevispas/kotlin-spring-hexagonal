package com.example.kotlinspringhexagonal

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountJpaEntity, Long> {


    fun findFirstByAccountNumber(accountNumber: Long): AccountJpaEntity?

}
