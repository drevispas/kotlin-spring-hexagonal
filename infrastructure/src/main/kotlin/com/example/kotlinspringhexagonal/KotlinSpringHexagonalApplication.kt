package com.example.kotlinspringhexagonal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * infrastructure라는 모듈 이름은 DDD layered architecture에서 부르는 가장 하위 layer와 이름이 같아서 헷갈릴 수도 있다.
 * 하지만, 관행적으로 infrastructure 모듈은 비지니스와 무관한 Spring 관련 객체들을 모아 놓는 곳이다.
 */
@SpringBootApplication
class KotlinSpringHexagonalApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringHexagonalApplication>(*args)
}
