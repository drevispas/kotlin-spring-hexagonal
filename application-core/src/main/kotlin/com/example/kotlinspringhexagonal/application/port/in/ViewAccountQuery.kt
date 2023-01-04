package com.example.kotlinspringhexagonal.application.port.`in`

import com.example.kotlinspringhexagonal.domain.Account

interface ViewAccountQuery {

    fun view(command: Query): Account

    data class Query(val accountNumber: Long)
}
