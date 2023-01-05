plugins {
    id("kotlin-common-conventions")
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

dependencies {
    // Coroutine 의존성
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("io.projectreactor:reactor-test")
}
