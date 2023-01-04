package com.example.kotlinspringhexagonal.application.port.out

import com.example.kotlinspringhexagonal.domain.Account

interface LoadAccountPort {

    fun load(accountNumber: Long): Account
}
