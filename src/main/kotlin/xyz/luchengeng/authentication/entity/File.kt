package xyz.luchengeng.authentication.entity

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class File (@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long?,
                 val fileName : String
)