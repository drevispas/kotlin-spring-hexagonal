package com.example.kotlinspringhexagonal.persistence

import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader

// 다른 subproject를 스캔할 수 있으며, 산출물(jar)에 있는 파일도 읽을 수 있다.
// Closeable 리소스의 경우 lambda block 에서 사용한 후 즉시 close() 되도록 한다.
// InputStreamReader 도 자동으로 close 된다.
// - https://stackoverflow.com/questions/11263926/closing-inputstreams-in-java
fun readResourceFile(fileInJar: String): String =
    ClassPathResource(fileInJar).inputStream.bufferedReader().use(BufferedReader::readText)
