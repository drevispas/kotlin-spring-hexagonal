package com.example.kotlinspringhexagonal.adapter.web

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import com.example.kotlinspringhexagonal.application.port.`in`.ViewAccountQuery
import com.example.kotlinspringhexagonal.domain.Account
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 가장 익숙한 Spring Web을 이용한 예제인데, Spring Webflux 버전으로 수정해도 된다.
 * @RestControllerAdvice 등으로 입력 유효성을 검증한다.
 */
@RestController
@RequestMapping("/account")
class AccountController(
    private val registerAccountUseCase: RegisterAccountUseCase,
    private val viewAccountQuery: ViewAccountQuery
) {

    // 테스트 편의 상 POST 대신 GET 사용
    @GetMapping("/{accountNumber}/{accountName}/{balanceAmount}")
    fun register(
        @PathVariable("accountNumber") accountNumber: Long,
        @PathVariable("accountName") userName: String,
        @PathVariable("balanceAmount") balanceAmount: Long
    ): RegisterAccountUseCase.Result {
        return registerAccountUseCase.register(RegisterAccountUseCase.Command(accountNumber, userName, balanceAmount))
    }

    @GetMapping("/{accountNumber}")
    fun view(@PathVariable("accountNumber") accountNumber: Long): Account {
        return viewAccountQuery.view(ViewAccountQuery.Query(accountNumber))
    }
}
