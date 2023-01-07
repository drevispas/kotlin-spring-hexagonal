package com.example.kotlinspringhexagonal.adapter.persistence

import org.springframework.data.jpa.repository.JpaRepository

/**
 * CrusRepository보다는 JpaRepository를 상속한다.
 * R2DBC의 경우에도 부모 인터페이스만 바뀔 뿐 사용법은 비슷하다.
 */
interface SpringDataAccountRepository : JpaRepository<AccountJpaEntity, Long> {

    fun findFirstByAccountNumber(accountNumber: Long): AccountJpaEntity?
}
