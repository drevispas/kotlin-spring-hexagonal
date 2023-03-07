# kotlin-spring-hexagonal

코프링 (코틀린+스프링)으로 헥사고널 아키텍처를 구현한 스켈레톤입니다.<br>
프로젝트마다 만들어야 하는 Gradle multi-project, depdency, package 구조는 boilerplate라고 생각하기 때문에 답처럼 정해진 틀을 제공합니다.

### 참조 모델: Hexagonal architerue
![Alt text](docs/images/hexagonal-architecture.png?raw=true "Hexagonal Architecture")

### 초기 설정
파일의 맨 마지막 줄에 공백 라인을 추가한다. Intellij 설정에서 Editor > General > "Ensure every saved file ends with a line break"를 체크한다.
![Alt text](docs/images/intellij-empty-last-line.png?raw=true "Empty last line")

Commit 전에 ktlint check을 수행하도록 git pre-commit hook을 추가한다.
```shell
./gradlew addKtlintCheckGitPreCommitHook
```

### 실행
Root project에서 bootRun을 실행하면 `:infrastructure:bootRun`이 실행된다.
```shell
./gradlew bootRun
```
