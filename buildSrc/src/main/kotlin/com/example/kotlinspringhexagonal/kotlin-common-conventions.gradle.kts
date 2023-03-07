import com.example.kotlinspringhexagonal.Version
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * Kotlin 프로젝트 공용 플러그인
 * - https://kotlinlang.org/docs/gradle-configure-project.html#targeting-the-jvm
 */

plugins {
    id("java-conventions")
    id("testing-conventions")

    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jlleitschuh.gradle.ktlint-idea")
    id("org.jetbrains.kotlinx.kover")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

dependencies {
    // Kotlin 의존성
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // 로깅 의존성
    implementation("io.github.microutils:kotlin-logging-jvm:${Version.KOTLIN_LOGGING}")

    // 입력 검증
    // https://github.com/konform-kt/konform
    implementation("io.konform:konform-jvm:${Version.KONFORM}")
}

// Spring initializer에 의해 자동으로 추가된 라인들 가져 옴
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = Version.JDK
    }
}

// .gitkeep 파일 중복
// https://stackoverflow.com/questions/67265308/gradle-entry-classpath-is-a-duplicate-but-no-duplicate-handling-strategy-has-b
tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

// Kover 설정
// https://kotlin.github.io/kotlinx-kover/
