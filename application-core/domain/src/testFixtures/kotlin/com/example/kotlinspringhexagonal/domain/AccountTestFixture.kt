package com.example.kotlinspringhexagonal.domain

object AccountTestFixture {

    const val ACCOUNT_NAME = "account1"

    // Best Practices for Unit Testing in Kotlin
    // - https://phauer.com/2018/best-practices-unit-testing-kotlin/
    // - https://resources.jetbrains.com/storage/products/kotlinconf2018/slides/4_Best%20Practices%20for%20Unit%20Testing%20in%20Kotlin.pdf
    fun createAccount(
        accountNumber: Long = 1001,
        accountName: String = ACCOUNT_NAME,
        balanceAmount: Long = 100,
    ) = Account(
        accountId = Account.AccountId(accountNumber),
        accountName = accountName,
        balance = Money(balanceAmount)
    )
}
