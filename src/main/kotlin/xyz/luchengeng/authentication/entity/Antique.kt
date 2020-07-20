package xyz.luchengeng.authentication.entity

import javax.persistence.*

@Entity
data class Antique(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        val type: AntiqueType,
        val name : String,
        val desp : String,
        var invalid : Boolean,
        @OneToMany(cascade = [CascadeType.PERSIST]) val verificationProcesses: MutableList<VerificationProcess>,
        @OneToOne(cascade = [CascadeType.PERSIST]) val user: User,
        @Column(columnDefinition = "LONGBLOB")
        val pic : ByteArray,
        @Column(columnDefinition = "LONGBLOB")
        var cert : ByteArray?,
        @OneToOne(cascade = [CascadeType.PERSIST])
        var wearAndTear: WearAndTear?
        ){
    constructor(antiqueDto: AntiqueDto,user : User) : this(id = null,type = antiqueDto.type,name = antiqueDto.name,desp = antiqueDto.desp,verificationProcesses = mutableListOf<VerificationProcess>(),user = user,pic=antiqueDto.pic!!,invalid = false,cert = null,wearAndTear = null)
}

enum class AntiqueType(s: String) {
    PORCELAIN("PORCELAIN"),
    JADE("JADE"),
    PAINTING_CALLIGRAPHY("PAINTING_CALLIGRAPHY"),
    METAL("METAL"),
    MISC("MISC")
}

data class AntiqueDto(
        val type: AntiqueType,
        val name : String,
        val desp : String,
        val invalid : Boolean?,
        var verificationProcesses : Int,
        val pic : ByteArray?,
        val userName : String?,
        val id : Long,
        var wearAndTear: WearAndTear?
){
    constructor(antique: Antique) : this(antique.type,antique.name,antique.desp,antique.invalid,antique.verificationProcesses.size?:0,null,antique.user.name,antique.id!!,wearAndTear=antique.wearAndTear)
}

