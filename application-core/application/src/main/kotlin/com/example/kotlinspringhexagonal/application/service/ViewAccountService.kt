package com.example.kotlinspringhexagonal.application.service

import com.example.kotlinspringhexagonal.application.port.`in`.ViewAccountQuery
import com.example.kotlinspringhexagonal.application.port.out.LoadAccountPort
import org.springframework.stereotype.Service

@Service
class ViewAccountService(
    private val loadAccountPort: LoadAccountPort
) : ViewAccountQuery {
    override fun view(query: ViewAccountQuery.Query): ViewAccountQuery.Result {
        val loadedAccount = loadAccountPort.load(query.accountNumber)
        return ViewAccountQuery.fromDomainEntity(loadedAccount)
    }
}
