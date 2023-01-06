# kotlin-spring-hexagonal

코프링 (코틀린+스프링)으로 헥사고널 아키텍처를 구현한 스켈레톤입니다.<br>
프로젝트마다 만들어야 하는 Gradle multi-project, depdency, package 구조는 boilerplate라고 생각하기 때문에 답처럼 정해진 틀을 제공합니다.

### 초기 설정
Commit 전에 ktlint check을 수행하도록 git pre-commit hook을 추가한다.
```shell
./gradlew addKtlintCheckGitPreCommitHook
```

### 실행
Root project에서 bootRun을 실행하면 `:infrastructure:bootRun`이 실행된다.
```shell
./gradlew bootRun
```
