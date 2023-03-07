package com.example.kotlinspringhexagonal.adapter.persistence

import com.example.kotlinspringhexagonal.domain.AccountTestFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql

// TODO: Junit5 대신 Kotest 사용하도록 변경
@DataJpaTest
@Import(AccountPersistenceAdapter::class)
internal class AccountPersistenceAdapterJunitTest() {
    @Autowired
    private lateinit var adapter: AccountPersistenceAdapter
    private lateinit var repository: SpringDataAccountRepository

    @Test
    fun testAdd() {
        assertEquals(5, Integer.sum(2, 3))
    }

    @Test
    @Sql("load-data.sql")
    fun load() {

        val actual = adapter.load(1001)
        val expected = AccountTestFixture.createAccount()
        assertThat(actual).isEqualTo(expected)
    }
}
