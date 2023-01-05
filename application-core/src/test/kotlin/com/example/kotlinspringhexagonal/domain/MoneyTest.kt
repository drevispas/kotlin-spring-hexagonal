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
        // describe: # 뒤에 member fun 이름을 써 준다.
        describe("#plus") {
            // context: 테스트하려고 하는 상황을 기술한다.
            context("when adding two money") {
                beforeEach {
                    // setUp. teadDown을 beforeEach, afterEach로 표현한다.
                }
                // it: should로 시작하는 예상 결과를 기술한다.
                it("should return the sum of two money") {
                    val amount1 = 12L
                    val amount2 = 34L
                    val actual = Money(amount1) + Money(amount2)
                    val expected = Money(amount1 + amount2)
                    // assertJ와 같은 assertion을 kotest가 kotlin DSL을 이용하여 제공해준다.
                    actual shouldBe expected
                }
            }
        }
    }
}
