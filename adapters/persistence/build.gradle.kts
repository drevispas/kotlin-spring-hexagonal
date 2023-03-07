import com.example.kotlinspringhexagonal.Version

plugins {
    id("kotlin-spring-conventions")
    // Gradle test fixtures 설정
    // - https://docs.gradle.org/current/userguide/java_testing.html#sec:java_test_fixtures
    // - https://leanmind.es/en/blog/test-fixtures-with-gradle/
    id("java-test-fixtures")
}

dependencies {
    // subproject 에 의존성 연결하기
    implementation(project(":common"))
    implementation(project(":application-core:application"))
    implementation(project(":application-core:domain"))

    // Spring 의존성
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // DB 의존성
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

    // Import test fixtures from domain
    testImplementation(testFixtures(project(":application-core:domain")))

    // Declare dependencies for this test fixture
    // testRuntimeOnly 와 동일한 효과
    // - https://toss.tech/article/how-to-manage-test-dependency-in-gradle
    testFixturesRuntimeOnly("com.h2database:h2")
    testFixturesImplementation("com.zaxxer:HikariCP:5.0.1")
    testFixturesImplementation("org.testcontainers:testcontainers")
    testFixturesImplementation("org.testcontainers:mysql")

    testFixturesImplementation("org.springframework.boot:spring-boot-starter-jdbc")
    testFixturesImplementation("mysql:mysql-connector-java")

//    testFixturesImplementation("org.testcontainers:r2dbc")
//    testFixturesImplementation("dev.miku:r2dbc-mysql:${Version.R2DBC_MYSQL}")
//    testFixturesImplementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

    testFixturesImplementation("io.github.microutils:kotlin-logging-jvm:${Version.KOTLIN_LOGGING}")
    testFixturesImplementation("io.kotest:kotest-runner-junit5:${Version.KOTEST}")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
    testFixturesImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${Version.TESTCONTAINERS}")
    }
}
