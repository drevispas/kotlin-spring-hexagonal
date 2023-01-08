import com.example.kotlinspringhexagonal.Version

plugins {
    id("kotlin-spring-conventions")
    // Gradle test fixtures 설정
    // - https://docs.gradle.org/current/userguide/java_testing.html#sec:java_test_fixtures
    // - https://leanmind.es/en/blog/test-fixtures-with-gradle/
    id("java-test-fixtures")
}

dependencies {
    // subproject에 의존성 연결하기
    implementation(project(":common"))
    implementation(project(":application-core:application"))
    implementation(project(":application-core:domain"))

    // Spring 의존성
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // DB 의존성
    runtimeOnly("com.h2database:h2")
//    testImplementation("com.h2database:h2")

    // Test fixtures
    testImplementation(testFixtures(project(":application-core:domain")))
//    testImplementation(testFixtures(project(":adapters:persistence")))
    // testRuntimeOnly와 동일한 효과
    // - https://toss.tech/article/how-to-manage-test-dependency-in-gradle
    testFixturesRuntimeOnly("com.h2database:h2")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${Version.TESTCONTAINERS}")
    }
}
