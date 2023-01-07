plugins {
    id("kotlin-common-conventions")
}

dependencies {
    // subproject에 의존성 연결하기
    implementation(project(":common"))
}
