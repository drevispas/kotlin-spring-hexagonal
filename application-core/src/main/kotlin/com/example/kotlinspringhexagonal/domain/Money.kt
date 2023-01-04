package com.example.kotlinspringhexagonal.domain

data class Money(val amount: Int) {
    companion object {
        val ZERO = Money(0)
    }
}

operator fun Money.plus(money: Money) = Money(amount + money.amount)

operator fun Money.minus(money: Money) = Money(amount - money.amount)
