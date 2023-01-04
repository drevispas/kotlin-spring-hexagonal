package com.example.kotlinspringhexagonal.application.service

import com.example.kotlinspringhexagonal.application.port.`in`.ViewAccountQuery
import com.example.kotlinspringhexagonal.domain.Account
import org.springframework.stereotype.Service

@Service
class ViewAccountService:ViewAccountQuery {
    override fun view(command: ViewAccountQuery.Query): Account {
        TODO("Not yet implemented")
    }
}
