import com.example.kotlinspringhexagonal.Version
import gradle.kotlin.dsl.accessors._0ef8f44799d218292e69168ba447ac3a.implementation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

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
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

dependencies {
    // Kotlin 의존성
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // 로깅 의존성
    implementation("io.github.microutils:kotlin-logging-jvm:${Version.KOTLIN_LOGGIN}")
}

// Spring initializer에 의해 자동으로 추가된 라인들 가져 옴
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = Version.JDK
    }
}

//tasks.register("installGitHook", Copy::class) {
//    from(File(rootProject.rootDir, "scripts/pre-commit"))
//    into(File(rootProject.rootDir, ".git/hooks"))
//    fileMode = 777
//}
//tasks.getByPath("preBuild").dependsOn("installGitHook")
