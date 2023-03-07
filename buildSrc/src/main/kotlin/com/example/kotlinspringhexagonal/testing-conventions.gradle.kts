import com.example.kotlinspringhexagonal.Version
import gradle.kotlin.dsl.accessors._c43f3ad1602c8daec5bb10fb63df8e28.implementation

plugins {
    id("java-conventions")
}

dependencies {
    // 테스팅 의존성
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Version.JUPITER}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Version.JUPITER}")
    testImplementation("io.kotest:kotest-runner-junit5:${Version.KOTEST}")
    testImplementation("io.kotest:kotest-assertions-core:${Version.KOTEST}")
    testImplementation("io.mockk:mockk:${Version.MOCKK}")
    testImplementation("io.github.microutils:kotlin-logging-jvm:${Version.KOTLIN_LOGGING}")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
