package xyz.luchengeng.authentication.entity

import javax.persistence.*

@Entity
data class WearAndTear(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        @OneToOne var user: User?,
        val content : String?
        )