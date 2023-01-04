package com.example.kotlinspringhexagonal.domain

data class Account(
    val accountId: AccountId,
    val accountName: String,
    val balance: Money = Money.ZERO
) {

    fun deposit(money: Money) = copy(balance = balance + money)

    fun withdraw(money: Money): Account {
        if (!isWithdrawable(money)) throw IllegalArgumentException("Too much to withdraw!")
        return copy(balance = balance - money)
    }

    private fun isWithdrawable(money: Money) = (balance - money).amount >= 0

    data class AccountId(val number: Long, val seq: Long = -1L)
}
