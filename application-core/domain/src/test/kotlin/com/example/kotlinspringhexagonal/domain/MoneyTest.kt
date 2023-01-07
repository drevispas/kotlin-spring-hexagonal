package com.example.kotlinspringhexagonal.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

/**
 * kotest 는 given/when/then 스타일 뿐만 아니라 다양한 스타일을 제공한다.
 * 우리는 그 중에서 아래의 better spec을 추종하도록 한다.
 * - https://www.betterspecs.org/
 *
 * TODO: Money는 간단하지만 필드가 많은 entity나 vo를 기본값으로 생성해주는 helper function을
 *  만들어 두는 것이 유용하다. Domain 객체에 대한 헬퍼는 다른 layer에서도 자주 사용되므로 domain에
 *  testFixtures를 만드는 것을 고려한다.
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
