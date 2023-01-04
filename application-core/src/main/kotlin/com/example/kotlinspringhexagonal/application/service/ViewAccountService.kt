package com.example.kotlinspringhexagonal.application.service

import com.example.kotlinspringhexagonal.application.port.`in`.ViewAccountQuery
import com.example.kotlinspringhexagonal.application.port.out.LoadAccountPort
import com.example.kotlinspringhexagonal.domain.Account
import org.springframework.stereotype.Service

@Service
class ViewAccountService(
    private val loadAccountPort: LoadAccountPort
) : ViewAccountQuery {
    override fun view(query: ViewAccountQuery.Query): Account {
        return loadAccountPort.load(query.accountNumber)
    }
}
