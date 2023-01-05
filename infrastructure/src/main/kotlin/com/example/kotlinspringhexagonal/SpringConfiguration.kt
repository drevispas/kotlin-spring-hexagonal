package com.example.kotlinspringhexagonal

import org.springframework.context.annotation.Configuration

/**
 * @Component 계열로 bean을 생성하지 않을 경우, @Configuration 클래스에서 함수 형태로 생성할 수 있다.
 * 각자 장단점이 있지만 결국 목적은 application context에 bean을 등록하는 것이다.
 */
@Configuration
class SpringConfiguration {
}
