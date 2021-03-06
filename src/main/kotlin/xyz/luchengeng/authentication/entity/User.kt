package xyz.luchengeng.authentication.entity

import javax.persistence.*

enum class UserType(s: String) {
    INDIVIDUAL("INDIVIDUAL"),
    AUCTIONEER("AUCTIONEER"),
    ANTIQUE_STORE("ANTIQUE_STORE"),
    ARCH_DEPT("ARCH_DEPT"),
    JUDICIAL_DEPT("JUDICIAL_DEPT"),
    ADMIN("ADMIN"),
    AUTH_CENTER("AUTH_CENTER")
}
@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
                var type : UserType,
                val name : String,
                @OneToMany(cascade = [CascadeType.PERSIST]) val verificationProcesses: MutableList<VerificationProcess>,
                @OneToOne(cascade = [CascadeType.PERSIST]) val info : UserInfo,
                @ElementCollection val verifiable : MutableList<VerificationProcessStage>
                )

@Entity
data class Credential(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
                      @OneToOne(cascade = [CascadeType.PERSIST]) val user : User,
                      var password : String
                      )
@Entity
data class UserInfo(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        val cell : String,
        val realName : String
)