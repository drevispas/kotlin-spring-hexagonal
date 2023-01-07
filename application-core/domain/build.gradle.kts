plugins {
    id("kotlin-common-conventions")
    // Gradle test fixtures 설정
    // - https://docs.gradle.org/current/userguide/java_testing.html#sec:java_test_fixtures
    // - https://leanmind.es/en/blog/test-fixtures-with-gradle/
    id("java-test-fixtures")
}

dependencies {
    // subproject에 의존성 연결하기
    implementation(project(":common"))
}
