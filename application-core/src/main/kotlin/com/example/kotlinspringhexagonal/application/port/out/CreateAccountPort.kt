package com.example.kotlinspringhexagonal.application.port.out

import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money

/**
 * ISP 원칙에 따라 기능별로 세분화한다.
 */
interface CreateAccountPort {

    fun create(accountNumber: Long, accountName: String, balanceAmount: Long): Account
}
