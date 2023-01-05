package com.example.kotlinspringhexagonal.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

/**
 * kotest 는 given/when/then 스타일 뿐만 아니라 다양한 스타일을 제공한다.
 * 우리는 그 중에서 아래의 better spec을 추종하도록 한다.
 * - https://www.betterspecs.org/
 */
class MoneyTest : DescribeSpec() {
    init {
        describe("#plus") {
            context("when adding two money") {
                beforeEach {
                    // setUp. teadDown을 beforeEach, afterEach로 표현한다.
                }
                it("should return the sum of two money") {
                    val amount1 = 12L
                    val amount2 = 34L
                    val actual = Money(amount1) + Money(amount2)
                    val expected = Money(amount1 + amount2)
                    actual shouldBe expected
                }
            }
        }
    }
}
