import com.example.kotlinspringhexagonal.Version
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * Kotlin 프로젝트 공용 플러그인
 * - https://kotlinlang.org/docs/gradle-configure-project.html#targeting-the-jvm
 */

plugins {
    id("java-conventions")

    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm")
}

dependencies {
    // Kotlin 의존성
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // 로깅 의존성
    implementation("io.github.microutils:kotlin-logging-jvm:${Version.KOTLIN_LOGGIN}")

    // 테스팅 의존성
    testImplementation("org.junit.jupiter:junit-jupiter:${Version.JUPITER}")
}

// Spring initializer에 의해 자동으로 추가된 라인들 가져 옴
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = Version.JDK
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
