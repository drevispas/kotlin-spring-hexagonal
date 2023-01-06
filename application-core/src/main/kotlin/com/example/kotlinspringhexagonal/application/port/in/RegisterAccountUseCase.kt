package com.example.kotlinspringhexagonal.application.port.`in`

import com.example.kotlinspringhexagonal.domain.Account
import com.example.kotlinspringhexagonal.domain.AccountStatus
import io.konform.validation.Invalid
import io.konform.validation.Validation
import io.konform.validation.jsonschema.minLength
import io.konform.validation.jsonschema.minimum
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

/**
 * Command usecase (비지니스 상태를 바꾸는 유스케이스)인 경우 접미사 "UseCase"를 사용한다.
 *
 * applictation.port.in 과 같은 패키지 경로도 그대로 사용하기로 한다.
 * 프로젝트끼리 동일한 패키지 구조를 가진다면 구성원 간에 코드를 이해하는 속도가 크게 증가한다.
 */
interface RegisterAccountUseCase {

    fun register(command: Command): Result

    /**
     * web -> usecase로 요청 데이터를 넘길 때에는 DTO로서 command 객체를 사용하여야 한다.
     */
    data class Command(val accountNumber: Long, val accountName: String, val balanceAmount: Long = 0L) {

        /**
         * 입력 유효성 검증은 incoming adapter <-> usecase 사이에 존재하는 incoming port 영역에서 처리한다.
         */
        init {
            val validationResult = validateCommand(this)
            logger.info { validationResult }
            if (validationResult is Invalid) {
                throw IllegalArgumentException(validationResult.errors.joinToString() { "[${it.dataPath} : ${it.message}]" })
            }
        }
    }

    /**
     * usecase --> web으로의 출력 모델도 usecase 별로 각자 정의한다. 가능한 최소한의 데이터를 노출하도록 설계하여야 한다.
     */
    data class Result(val accountSatus:AccountStatus, val accountData:AccountData) {
        data class AccountData(val accountNumber: Long, val accountName: String, val depositAmount: Long)
    }

    companion object {
        val validateCommand = Validation<Command> {
            Command::accountName {
                minLength(2)
            }

            Command::balanceAmount ifPresent {
                minimum(0)
            }
        }

        fun fromDomainEntity(account: Account) = Result(
            AccountStatus.REGISTERING,
            Result.AccountData(account.accountId.number, account.accountName, account.balance.amount)
        )
    }
}
