plugins {
    id("kotlin-application-conventions")
    id("spring-conventions")
}

dependencies {
    // subproject에 의존성 연결하기
    implementation(project(":common"))
}
