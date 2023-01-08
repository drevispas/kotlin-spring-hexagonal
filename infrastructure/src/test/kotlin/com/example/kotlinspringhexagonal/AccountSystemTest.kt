package com.example.kotlinspringhexagonal

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import com.example.kotlinspringhexagonal.application.port.`in`.ViewAccountQuery
import com.example.kotlinspringhexagonal.domain.AccountStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AccountSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun testRegisterAccount() {
        val response = restTemplate.getForEntity(
            "/accounts/1004/account1/400",
            RegisterAccountUseCase.Result::class.java
        )
        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    @Sql("persistence/create-account.sql")
    fun testViewAccount() {
        val response = restTemplate.getForEntity(
            "/accounts/1001",
            ViewAccountQuery.Result::class.java
        )
        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(response.body?.accountSatus ?: AccountStatus.UNSPECIFIED, AccountStatus.ACTIVE)
    }
}
