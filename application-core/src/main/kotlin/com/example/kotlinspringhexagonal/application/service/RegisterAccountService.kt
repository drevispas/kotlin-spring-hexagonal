package com.example.kotlinspringhexagonal.application.service

import com.example.kotlinspringhexagonal.application.port.`in`.RegisterAccountUseCase
import com.example.kotlinspringhexagonal.application.port.out.CreateAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import org.springframework.stereotype.Service

@Service
class RegisterAccountService(
    private val createAccountPort: CreateAccountPort
):RegisterAccountUseCase {
    override fun register(command: RegisterAccountUseCase.Command): Account {

        val account = createAccountPort.create(command.accountNumber, command.accountName, command.balance)
        return createAccountPort.create(account.accountId.value, account.accountName, account.balance)
    }
}
