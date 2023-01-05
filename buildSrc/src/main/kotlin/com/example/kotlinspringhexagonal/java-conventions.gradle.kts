import com.example.kotlinspringhexagonal.Version

plugins {
    java
}

java {
    // Auto JDK setup
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(Version.JDK))
    }
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}
