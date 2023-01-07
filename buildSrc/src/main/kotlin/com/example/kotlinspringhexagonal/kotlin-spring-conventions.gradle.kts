import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("kotlin-application-conventions")

    // Classes annotated with @Configuration, @Controller, @RestController, @Service or @Repository are automatically opened
    // https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support
    kotlin("plugin.spring")
    // https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
    kotlin("plugin.jpa")

    // Allows to package executable jar or war archives, run Spring Boot applications, and use the dependency management
    // https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/
    id("org.springframework.boot")
}

logger.lifecycle("Enabling Kotlin Spring plugin in module ${project.path}")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

logger.lifecycle("Enabling Spring Boot plugin in module ${project.path}")
apply(plugin = "org.springframework.boot")

logger.lifecycle("Enabling Spring Boot Dependency Management in module ${project.path}")
apply(plugin = "io.spring.dependency-management")

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.named<BootRun>("bootRun") {
    enabled = false
}

// 스프링 의존성이 있더라도 기본적으로는 bootJar, bootRun 태스크를 skip하게 한다.
springBoot {
    // Creates META-INF/build-info.properties for Spring Boot Actuator
    buildInfo()
}

dependencies {
    implementation("org.springframework:spring-context")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
