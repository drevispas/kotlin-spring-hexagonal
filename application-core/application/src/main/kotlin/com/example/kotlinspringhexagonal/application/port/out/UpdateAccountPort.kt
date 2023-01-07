package com.example.kotlinspringhexagonal.application.port.out

import com.example.kotlinspringhexagonal.domain.Account

interface UpdateAccountPort {

    fun update(accountNumber: Long, accountName: String): Account
}
