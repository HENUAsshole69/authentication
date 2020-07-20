package xyz.luchengeng.authentication.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class WearAndTear(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        val content : String?
        )