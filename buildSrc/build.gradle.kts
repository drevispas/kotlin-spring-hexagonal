/*
 * buildSrc에서 자체 plugin을 만들기 위한 스크립트
 */

plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
}

// gradlePluginPortal 리포로부터 사용할 plugin들을 명시한다.
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.8.0")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.0.1")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:11.0.0")
}
