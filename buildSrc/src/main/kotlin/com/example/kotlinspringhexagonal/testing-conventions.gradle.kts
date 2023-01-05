import com.example.kotlinspringhexagonal.Version

plugins {
    id("java-conventions")
}

dependencies {
    // 테스팅 의존성
    testImplementation("org.junit.jupiter:junit-jupiter:${Version.JUPITER}")
    testImplementation("io.kotest:kotest-runner-junit5:${Version.KOTEST}")
    testImplementation("io.kotest:kotest-assertions-core:${Version.KOTEST}")
    testImplementation("io.mockk:mockk:${Version.MOCKK}")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
