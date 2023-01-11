package com.example.kotlinspringhexagonal.adapter.web

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import com.example.kotlinspringhexagonal.application.port.`in`.ViewAccountQuery
import com.example.kotlinspringhexagonal.domain.AccountTestFixture
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

// TODO: Junit5 대신 Kotest 사용하도록 변경

// https://www.baeldung.com/spring-rest-docs
// @ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@AutoConfigureRestDocs
@WebMvcTest(controllers = [AccountController::class])
internal class AccountControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var registerAccountUseCase: RegisterAccountUseCase

    @MockBean
    private lateinit var viewAccountQuery: ViewAccountQuery

//    @BeforeEach
//    fun setUp(webApplicationContext: WebApplicationContext, restDocumentation: RestDocumentationContextProvider) {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
//    }

    @Test
    fun testRegisterAccount() {
        val command = RegisterAccountUseCase.Command(
            AccountTestFixture.ACCOUNT_NUMBER,
            AccountTestFixture.ACCOUNT_NAME,
            AccountTestFixture.BALANCE_AMOUNT
        )
        val accountResult = RegisterAccountUseCase.fromDomainEntity(AccountTestFixture.createAccount())
        Mockito.`when`(registerAccountUseCase.register(command)).thenReturn(accountResult)

        val url =
            "/accounts/${AccountTestFixture.ACCOUNT_NUMBER}/${AccountTestFixture.ACCOUNT_NAME}/${AccountTestFixture.BALANCE_AMOUNT}"
        mockMvc.get(url) {
            accept = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                jsonPath("$.accountData.accountNumber") { value(AccountTestFixture.ACCOUNT_NUMBER) }
                jsonPath("$.accountData.accountName") { value(AccountTestFixture.ACCOUNT_NAME) }
                jsonPath("$.accountData.depositAmount") { value(AccountTestFixture.BALANCE_AMOUNT) }
            }
        }.andDo {
            handle(document("accounts/register-account"))
        }
    }
}
