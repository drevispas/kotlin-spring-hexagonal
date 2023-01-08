package com.example.kotlinspringhexagonal.adapter.web

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import com.example.kotlinspringhexagonal.application.port.`in`.ViewAccountQuery
import com.example.kotlinspringhexagonal.domain.AccountTestFixture
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

// TODO: Junit5 대신 Kotest 사용하도록 변경
@WebMvcTest(controllers = [AccountController::class])
internal class AccountControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var registerAccountUseCase: RegisterAccountUseCase

    @MockBean
    private lateinit var viewAccountQuery: ViewAccountQuery

    @Test
    fun testRegisterAccount() {
        val command = RegisterAccountUseCase.Command(1001, "brad", 100)
        val accountResult = RegisterAccountUseCase.fromDomainEntity(AccountTestFixture.createAccount())
        Mockito.`when`(registerAccountUseCase.register(command)).thenReturn(accountResult)

        mockMvc.get("/accounts/1001/brad/100") {
            accept = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }
}
