package com.example.kotlinspringhexagonal.application.port.`in`

import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.AccountStatus
import io.konform.validation.Invalid
import io.konform.validation.Validation
import io.konform.validation.jsonschema.minimum

/**
 * Qauery usecase (비지니스 상태를 바꾸지 않고 조회만)인 경우에는 접미사 "Query"를 사용한다.
 * CQRS를 위해서 command (수정), query (조회)를 incoming port 단계에서부터 나눈다.
 */
interface ViewAccountQuery {

    fun view(query: Query): Result

    /**
     * Usecase, Query마다 각자의 입력 모델을 정의한다.
     */
    data class Query(val accountNumber: Long) {
        init {
            val validationResult = validateQuery(this)
            if (validationResult is Invalid) {
                throw IllegalArgumentException(validationResult.errors.joinToString() { "[${it.dataPath} : ${it.message}]" })
            }
        }
    }

    /**
     * RegisterAccountUseCase의 출력 모델과 처음에는 동일하게 시작할지라도 개발이 진행되어 분기가 생기면 어차피 분리해야 한다.
     * 처음부터 usecase, query 별로 독립적인 입출력 모델을 선언하도록 한다.
     */
    data class Result(val accountSatus: AccountStatus, val accountData: AccountData) {
        data class AccountData(val accountNumber: Long, val accountName: String, val depositAmount: Long)
    }

    companion object {
        val validateQuery = Validation<Query> {
            Query::accountNumber {
                minimum(2)
            }
        }

        fun fromDomainEntity(account: Account) = Result(
            AccountStatus.ACTIVE,
            Result.AccountData(
                account.accountId.number,
                account.accountName,
                account.balance.amount
            )
        )
    }
}
