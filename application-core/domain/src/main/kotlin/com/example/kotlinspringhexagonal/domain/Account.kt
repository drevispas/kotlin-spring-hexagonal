package com.example.kotlinspringhexagonal.domain

/**
 * 헥사고날 아키텍처의 중심을 application-core라고 하며 가장 안쪽의 domain 역영과 바로 바깥의 application 영역을 합쳐 부르는 말이다.
 * DDD에 따라 domain 영역은 domain entity와 value object들로 구성된다.
 * 이러한 객체들이 같은 비지니스 그룹으로 묶일 때 root 객체를 aggregate root entity라고 부른다.
 * 비지니스 로직을 작성할 때는 aggregate root를 가져와서 읽고 수정한 다음, aggregate 단위 통째로 DB에 저장하여야 한다.
 * 비지니스를 우선으로 하는 개발을 위해서는 가장 안쪽에 있는 domain 객체들을 가장 먼저 개발하고 바깥쪽으로 이동하여야 한다.
 *
 * Account처럼 ID를 가지고 객체 생명주기가 있는 도메인 객체를 도메인 엔터티라고 부른다.
 */
data class Account(
    val accountId: AccountId,
    val accountName: String,
    val balance: Money = Money.ZERO
) {

    fun deposit(money: Money) = copy(balance = balance + money)

    /**
     * 함수 각각이 비지니스 로직을 구현한다.
     */
    fun withdraw(money: Money): Account {
        /* 비즈 로직 함수 안에서 비지니스 규칙을 검사한다. */
        if (!isWithdrawable(money)) throw IllegalArgumentException("Too much to withdraw!")
        return copy(balance = balance - money)
    }

    private fun isWithdrawable(money: Money) = (balance - money).amount >= 0

    data class AccountId(val number: Long)
}
