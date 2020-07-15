package xyz.luchengeng.authentication.entity

import javax.persistence.*

@Entity
data class Antique(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        val type: AntiqueType,
        val name : String,
        val desp : String,
        var invalid : Boolean,
        @OneToMany(cascade = [CascadeType.ALL]) var verificationProcesses: MutableList<VerificationProcess>,
        @OneToOne(cascade = [CascadeType.ALL]) var user: User,
        @Column(columnDefinition = "LONGBLOB")
        val pic : ByteArray
        ){
    constructor(antiqueDto: AntiqueDto,user : User) : this(id = null,type = antiqueDto.type,name = antiqueDto.name,desp = antiqueDto.desp,verificationProcesses = mutableListOf<VerificationProcess>(),user = user,pic=antiqueDto.pic!!,invalid = false)
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
        val id : Long
){
    constructor(antique: Antique) : this(antique.type,antique.name,antique.desp,antique.invalid,antique.verificationProcesses.size?:0,null,antique.id!!)
}

