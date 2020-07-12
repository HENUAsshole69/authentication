package xyz.luchengeng.authentication.repo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import xyz.luchengeng.authentication.entity.Antique
import xyz.luchengeng.authentication.entity.User

interface UserRepo : JpaRepository<User,Long>{
    @Query("select u from User u where not count(u.verificationProcesses) = 0")
    fun findUserNeedsVerification(pageable : Pageable) : Page<User>
}