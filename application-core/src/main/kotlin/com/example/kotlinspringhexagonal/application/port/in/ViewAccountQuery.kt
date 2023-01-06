package com.example.kotlinspringhexagonal.application.port.`in`

import com.example.kotlinspringhexagonal.domain.Account

/**
 * Qauery usecase (비지니스 상태를 바꾸지 않고 조회만)인 경우에는 접미사 "Query"를 사용한다.
 * CQRS를 위해서 command (수정), query (조회)를 incoming port 단계에서부터 나눈다.
 */
interface ViewAccountQuery {

    fun view(query: Query): Account

    /**
     * Usecase, Query마다 각자의 입력 모델을 정의한다.
     */
    data class Query(val accountNumber: Long)
}
