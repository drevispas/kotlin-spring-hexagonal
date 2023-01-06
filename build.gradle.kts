plugins {
    id("kotlin-common-conventions")
}

// Root project에는 koverMergedXxx 태스크들을 추가한다.
// `./gradlw koverMergedReport` 를 실행해서 커버리지 보고서를 볼 수 있다.
// https://kotlin.github.io/kotlinx-kover/
koverMerged {
    enable()
}
