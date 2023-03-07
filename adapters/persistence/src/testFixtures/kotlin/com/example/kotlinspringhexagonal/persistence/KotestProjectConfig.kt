package com.example.kotlinspringhexagonal.persistence

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.listeners.BeforeProjectListener
import io.kotest.core.listeners.BeforeTestListener
import io.kotest.core.spec.IsolationMode
import io.kotest.extensions.spring.SpringExtension

// 테스트 클래스나 메써드 단위가 아니라 글로벌 Kotest 설정을 하고 싶을 때
object KotestProjectConfig : AbstractProjectConfig() {

    override val isolationMode = IsolationMode.InstancePerTest

    // https://kotest.io/docs/extensions/spring.html
    override fun extensions() = listOf(SpringExtension, KotestProjectListner)
}

object KotestProjectListner : BeforeProjectListener, BeforeTestListener {

    // 속도 향상을 위해서 Mysql 컨테이너를 한 번만 시작시킨다.
    override suspend fun beforeProject() {
        // 컨테이너를 시작한다.
        MysqlTestContainer.run()
        // 시스템 환경변수를 설정한다.
        MysqlTestContainer.setProperties()
        // 스키마를 최초 1회 생성한다.
        MysqlTestContainer.initialize()
    }
}
