package com.example.kotlinspringhexagonal.persistence

import com.mysql.cj.jdbc.MysqlDataSource
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import mu.KotlinLogging
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy
import java.time.Duration
import java.time.temporal.ChronoUnit

val ktLogger = KotlinLogging.logger { }

class MysqlTestContainer : MySQLContainer<Nothing>("mysql:latest") {

    override fun waitUntilContainerStarted() {
        LogMessageWaitStrategy()
            .withRegEx(".*ready for connections.*\\s")
            .withTimes(4)
            .withStartupTimeout(Duration.of(TIMEOUT, ChronoUnit.SECONDS))
            .waitUntilReady(this)
    }

    companion object {

        const val USER_NAME = "mysql"
        const val PASS = "mysql"
        const val DATABASE_NAME = "test"
        const val TIMEOUT = 60L

        const val CLEAR_DATA_SCRIPT = "com/example/kotlinspringhexagonal/adapter/persistence/clear-data.sql"
        const val CREATE_SCHEMA_SCRIPT = "com/example/kotlinspringhexagonal/adapter/persistence/create-schema.sql"
        const val LOAD_DATA_SCRIPT = "com/example/kotlinspringhexagonal/adapter/persistence/load-data.sql"

        lateinit var instance: MysqlTestContainer
        lateinit var dataSource: HikariDataSource
//        lateinit var dataSource: MysqlDataSource
        lateinit var jdbcTemplate: JdbcTemplate

        fun run() {
            instance = MysqlTestContainer().apply {
                withDatabaseName(DATABASE_NAME)
                withUsername(USER_NAME)
                withPassword(PASS)
                withStartupTimeoutSeconds(TIMEOUT.toInt())
                // Container 생성 시 상세 로그를 보고 싶다면 아래를 comment in
//                withLogConsumer(Slf4jLogConsumer(ktLogger))
                start()
            }
            dataSource = getHikariDataSource()
//            dataSource = getMysqlDataSource()
            jdbcTemplate = JdbcTemplate(dataSource)
            ktLogger.info { "instance.jdbcUrl: ${instance.jdbcUrl}" }
        }

        fun initialize() {
            executeScript(CREATE_SCHEMA_SCRIPT)
        }

        fun beforeEach() {
            executeScript(CLEAR_DATA_SCRIPT)
            executeScript(LOAD_DATA_SCRIPT)
        }

        /**
         * can only execute a single statement
         */
        fun executeStatement(sql: String) {
            dataSource.connection.createStatement().run {
                ktLogger.info("Executing SQL: $sql")
                execute(sql)
                ktLogger.info("Finishing SQL: $sql")
            }

            // Jdbc template version
//            jdbcTemplate.execute(sql)
        }

        /**
         * can execute multiple statements in a file at once
         */
        fun executeScript(resourcePath: String) {
            ResourceDatabasePopulator(
                /* continueOnError = */ false,
                /* ignoreFailedDrops = */ false,
                /* sqlScriptEncoding = */ "UTF-8",
                /* ...scripts = */ ClassPathResource(resourcePath)
            ).execute(dataSource)
        }

        // For Kotest project configuration
        fun setProperties() {
            if (!this::instance.isInitialized) return
            with(instance) {
                System.setProperty("spring.datasource.url", jdbcUrl)
                System.setProperty("spring.datasource.username", username)
                System.setProperty("spring.datasource.password", password)
            }
        }

        // For Junit5
        // - https://dev.to/j_a_o_v_c_t_r/testcontainers-with-mysql-and-redis-with-an-spring-boot-kotlin-application-4bmf
        @DynamicPropertySource
        @JvmStatic
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", instance::getJdbcUrl)
            registry.add("spring.datasource.username", instance::getUsername)
            registry.add("spring.datasource.password", instance::getPassword)
        }

        /**
         * JPA 와 동일한 Hikari connection pool 을 사용하는 datasource를 얻는다.
         */
        private fun getHikariDataSource(): HikariDataSource = HikariDataSource(
            HikariConfig().apply {
                driverClassName = instance.driverClassName
                jdbcUrl = instance.jdbcUrl
                username = instance.username
                password = instance.password
            }
        )

        /**
         * Connection pool 을 사용하지 않는 datasource 를 얻는다.
         */
        private fun getMysqlDataSource(): MysqlDataSource = MysqlDataSource().apply {
            setURL(instance.jdbcUrl)
            user = instance.username
            password = instance.password
        }
    }
}
