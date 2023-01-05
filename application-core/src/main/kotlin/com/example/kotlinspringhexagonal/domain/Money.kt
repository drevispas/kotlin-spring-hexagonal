package com.example.kotlinspringhexagonal.domain

/**
 * Money와 같이 ID나 생명주기가 없는 도메인 객체를 Value Object라고 부른다.
 * 블록체인에 비유하자면 domain entity는 NFT이고, value object는 교환가능한 fungible token이다.
 */
data class Money(val amount: Long, val currency: Currency = Currency.KRW) {
    companion object {
        val ZERO = Money(0L)
    }
}

operator fun Money.plus(money: Money) = Money(amount + money.amount)

operator fun Money.minus(money: Money) = Money(amount - money.amount)
