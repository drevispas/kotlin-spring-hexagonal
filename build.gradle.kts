plugins {
    id("kotlin-common-conventions")
}

// Root project에는 koverMergedXxx 태스크들을 추가한다.
// https://kotlin.github.io/kotlinx-kover/
koverMerged {
    enable()
}
