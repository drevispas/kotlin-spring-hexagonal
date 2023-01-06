import com.example.kotlinspringhexagonal.Version

plugins {
    id("kotlin-spring-conventions")
}

// infrastructure만 bootJar를 생성하도록 한다.
tasks.bootJar { enabled = true }

dependencies {
    // subproject에 의존성 연결하기
    implementation(project(":common"))
    implementation(project(":adapters:web"))
    implementation(project(":adapters:persistence"))

    // Spring 의존성들
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // 데이터 의존성
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // 테스팅 의존성
    testImplementation("org.testcontainers:junit-jupiter")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${Version.TESTCONTAINERS}")
    }
}

// bootRun 태스크는 infrastructure 모듈에서만 동작하게 한다.
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = true
}

tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    enabled = true
}
