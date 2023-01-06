package com.example.kotlinspringhexagonal.application.port.`in`

import com.example.kotlinspringhexagonal.domain.Account

/**
 * Command usecase (비지니스 상태를 바꾸는 유스케이스)인 경우 접미사 UseCase를 사용한다.
 *
 * applictation.port.in 과 같은 패키지 경로도 그대로 사용하기로 한다.
 * 프로젝트끼리 동일한 패키지 구조를 가진다면 구성원 간에 코드를 이해하는 속도가 크게 증가한다.
 */
interface RegisterAccountUseCase {

    fun register(command: Command): Account

    data class Command(val accountNumber: Long, val accountName: String, val balanceAmount: Long = 0L)
}
