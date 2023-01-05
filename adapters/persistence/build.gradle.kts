import com.example.kotlinspringhexagonal.Version

plugins {
    id("kotlin-application-conventions")
    id("spring-conventions")
}

dependencies {
    // subproject에 의존성 연결하기
    implementation(project(":common"))
    implementation(project(":application-core"))

    // Spring 의존성
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // DB 의존성
    runtimeOnly("com.h2database:h2")
    testImplementation("com.h2database:h2")

    // 테스팅 의존성
    testImplementation("org.testcontainers:junit-jupiter")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${Version.TESTCONTAINERS}")
    }
}
