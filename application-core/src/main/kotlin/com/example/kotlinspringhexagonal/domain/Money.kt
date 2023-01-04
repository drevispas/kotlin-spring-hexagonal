package com.example.kotlinspringhexagonal.domain

data class Money(val amount: Long, val currency: Currency = Currency.KRW) {
    companion object {
        val ZERO = Money(0L)
    }
}

operator fun Money.plus(money: Money) = Money(amount + money.amount)

operator fun Money.minus(money: Money) = Money(amount - money.amount)
