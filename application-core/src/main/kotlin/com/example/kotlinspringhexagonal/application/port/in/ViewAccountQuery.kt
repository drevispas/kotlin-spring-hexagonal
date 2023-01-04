package com.example.kotlinspringhexagonal.application.port.`in`

import com.example.kotlinspringhexagonal.domain.Account

/**
 * CQRS를 위해서 command (수정), query (조회)를 incoming port에서부터 나눈다.
 */
interface ViewAccountQuery {

    fun view(query: Query): Account

    data class Query(val accountNumber: Long)
}
