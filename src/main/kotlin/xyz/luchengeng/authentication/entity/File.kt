package xyz.luchengeng.authentication.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class File (@Id @GeneratedValue(
        strategy = GenerationType.IDENTITY) val uuid: UUID,
        val fileName : String
)