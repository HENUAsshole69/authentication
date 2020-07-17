package xyz.luchengeng.authentication.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class LoggingEntry(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
                        val token: String,
                        var methodName: String,
                        val dateTime: LocalDateTime)

data class LoggingEntryDto(
        val userName: String?,
        val opName: String,
        val dateTime: LocalDateTime
)