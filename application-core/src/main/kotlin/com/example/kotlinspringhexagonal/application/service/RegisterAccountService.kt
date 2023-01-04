package com.example.kotlinspringhexagonal.application.service

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import com.example.kotlinspringhexagonal.application.port.out.CreateAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.Money
import org.springframework.stereotype.Service

@Service
class RegisterAccountService(
    private val createAccountPort: CreateAccountPort
):RegisterAccountUseCase {
    override fun register(command: RegisterAccountUseCase.Command): Account {

        val account = Account(Account.AccountId(command.accountNumber), command.accountName, Money(command.balanceAmount))
        return createAccountPort.create(account.accountId.number, account.accountName, account.balance.amount)
    }
}
