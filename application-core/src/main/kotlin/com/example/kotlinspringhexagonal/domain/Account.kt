package com.example.kotlinspringhexagonal.domain

/**
 * 비지니스를 우선으로 하는 개발을 위해서는 Domain entity를 가장 먼저 개발한다.
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

    data class AccountId(val number: Long, val seq: Long = -1L)
}
