package com.example.kotlinspringhexagonal.adapter.persistence

import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql

@DataJpaTest
@Import(AccountPersistenceAdapter::class)
internal class AccountPersistenceAdapterTest() {
    @Autowired
    private lateinit var adapter: AccountPersistenceAdapter
    private lateinit var repository: SpringDataAccountRepository

    @Test
    fun testAdd() {
        assertEquals(5, Integer.sum(2, 3))
    }

    @Test
    @Sql("create-account.sql")
    fun load() {

        val actual = adapter.load(1001)
        val expected = Account(Account.AccountId(1001, 1), "account1", Money(100))
        assertThat(actual).isEqualTo(expected)
    }
}
