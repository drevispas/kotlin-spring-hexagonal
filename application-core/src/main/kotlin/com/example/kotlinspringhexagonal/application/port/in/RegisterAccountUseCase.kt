package com.example.kotlinspringhexagonal.application.port.`in`

import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money

interface RegisterAccountUseCase {

    fun register(command: Command): Account

    data class Command(val accountNumber: Long, val accountName: String, val balanceAmount: Long = 0L)
}
