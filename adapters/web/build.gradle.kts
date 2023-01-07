plugins {
    id("kotlin-spring-conventions")
}

dependencies {
    // subproject에 의존성 연결하기
    implementation(project(":common"))
    implementation(project(":application-core:application"))
    implementation(project(":application-core:domain"))

    // Spring 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}
