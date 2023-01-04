package com.example.kotlinspringhexagonal.application.port.out

import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money

interface CreateAccountPort {

    fun create(accountNumber: Long, accountName: String, balanceAmount: Long): Account
}
