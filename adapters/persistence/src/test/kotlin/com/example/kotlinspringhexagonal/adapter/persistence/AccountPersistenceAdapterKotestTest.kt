package com.example.kotlinspringhexagonal.adapter.persistence

import com.example.kotlinspringhexagonal.domain.AccountTestFixture
import com.example.kotlinspringhexagonal.persistence.MysqlTestContainer
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import org.springframework.test.context.ContextConfiguration

val ktLogger = KotlinLogging.logger { }

@ContextConfiguration(classes = [TestApplication::class])
class AccountPersistenceAdapterKotestTest(adapter: AccountPersistenceAdapter) : DescribeSpec() {

    init {
        // 매 테스트 전에 데이터를 초기화한다.
        beforeEach {
            MysqlTestContainer.beforeEach()
        }
        describe("#load") {
            context("when adapter creates an account") {
                it("should be stored in database") {
                    val actual = adapter.load(AccountTestFixture.ACCOUNT_NUMBER)
                    val expected = AccountTestFixture.createAccount()
                    actual shouldBe expected
                }
            }
        }
    }
}
