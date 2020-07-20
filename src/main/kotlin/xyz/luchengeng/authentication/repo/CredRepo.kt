package xyz.luchengeng.authentication.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import xyz.luchengeng.authentication.entity.Credential

interface CredRepo : JpaRepository<Credential,Long> {
    fun findFirstByUserNameAndPassword(userName : String,password : String) : Credential?
    @Query("select c from Credential c where c.user.id = ?1")
    fun findCredByUserId(userId : Long) : Credential?
}