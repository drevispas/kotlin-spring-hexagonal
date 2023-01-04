package com.example.kotlinspringhexagonal.adapter.web

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import com.example.kotlinspringhexagonal.application.port.`in`.ViewAccountQuery
import com.example.kotlinspringhexagonal.domain.Account
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class AccountController(
    private val registerAccountUseCase: RegisterAccountUseCase,
    private val viewAccountQuery: ViewAccountQuery
) {

    // 테스트 편의 상 POST 대신 GET 사용
    @GetMapping("/users/{accountNumber}/{accountName}")
    fun register(@PathVariable("accountNumber") accountNumber: Long, @PathVariable("accountName") userName: String): Account {
        return registerAccountUseCase.register(RegisterAccountUseCase.Command(accountNumber, userName))
    }

    @GetMapping("/users/{accountNumber}")
    fun view(@PathVariable("accountNumber") accountNumber: Long): Account {
        return viewAccountQuery.view(ViewAccountQuery.Query(accountNumber))
    }

}
