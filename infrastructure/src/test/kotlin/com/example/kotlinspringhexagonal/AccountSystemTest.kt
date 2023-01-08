package com.example.kotlinspringhexagonal

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AccountSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun testRegisterAccount() {
        val response = restTemplate.getForEntity(
            "/accounts/1001/account1/100",
            RegisterAccountUseCase.Result::class.java
        )
        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
    }
}
