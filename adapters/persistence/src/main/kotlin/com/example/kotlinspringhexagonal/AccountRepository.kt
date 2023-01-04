package com.example.kotlinspringhexagonal

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface AccountRepository: JpaRepository<AccountRepository.AccountJpaEntity, Long> {

    @Entity
    @Table(name = "account")
    class AccountJpaEntity {

        @Id
        @GeneratedValue
        private val accountNumber: Long = 0L
    }
}
