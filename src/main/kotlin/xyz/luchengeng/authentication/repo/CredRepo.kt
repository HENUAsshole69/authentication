package xyz.luchengeng.authentication.repo

import org.springframework.data.jpa.repository.JpaRepository
import xyz.luchengeng.authentication.entity.Credential

interface CredRepo : JpaRepository<Credential,Long> {
    fun findFirstByUserNameAndPassword(userName : String,password : String) : Credential?
}