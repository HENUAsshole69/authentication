package xyz.luchengeng.authentication.entity

import javax.persistence.*

enum class UserType(s: String) {
    INDIVIDUAL("INDIVIDUAL"),
    AUCTIONEER("AUCTIONEER"),
    ANTIQUE_STORE("ANTIQUE_STORE"),
    ARCH_DEPT("ARCH_DEPT"),
    JUDICIAL_DEPT("JUDICIAL_DEPT"),
    ADMIN("ADMIN")
}
@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
                val type : UserType,
                val name : String,
                @OneToMany(cascade = [CascadeType.ALL]) val verificationProcesses: MutableList<VerificationProcess>,
                @OneToOne(cascade = [CascadeType.ALL]) val info : UserInfo)

@Entity
data class Credential(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
                      @OneToOne(cascade = [CascadeType.ALL]) val user : User,
                      val password : String
                      )
@Entity
data class UserInfo(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        val cell : String,
        val realName : String
)