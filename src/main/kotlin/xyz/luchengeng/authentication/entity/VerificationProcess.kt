package xyz.luchengeng.authentication.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class VerificationProcess(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        @OneToOne(cascade = [CascadeType.PERSIST]) val user : User,
        val time : LocalDateTime,
        val content : String,
        val stage : VerificationProcessStage
){
    fun toDto() : VerificationProcessDto{
        return VerificationProcessDto(id,content,stage)
    }
}

enum class VerificationProcessStage(s: String) {
    STAGE_ONE("STAGE_ONE"),
    STAGE_TWO("STAGE_TWO"),
    STAGE_THREE("STAGE_THREE")
}

data class VerificationProcessDto(
        val id : Long?,
        val content : String,
        val stage : VerificationProcessStage
){
    fun toEntity(user :User) : VerificationProcess{
        return VerificationProcess(id,user, LocalDateTime.now(),content,stage)
    }
}