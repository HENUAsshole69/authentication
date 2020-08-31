package xyz.luchengeng.authentication.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class Antique(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        val type: AntiqueType,
        val name : String,
        val desp : String,
        var invalid : Boolean,
        @OneToMany(cascade = [CascadeType.PERSIST]) val verificationProcesses: MutableList<VerificationProcess>,
        @OneToOne(cascade = [CascadeType.PERSIST]) val user: User?,
        @Column(columnDefinition = "LONGBLOB")
        val pic : UUID,
        @Column(columnDefinition = "LONGBLOB")
        var cert : UUID?,
        @OneToOne(cascade = [CascadeType.PERSIST])
        var wearAndTear: WearAndTear?,
        @OneToOne(cascade = [CascadeType.PERSIST])
        var owner : ApplierInfo?,
        val sourceInfo: String,
        val spec: String,
        val wholeness: String,
        val era:String,
        val authType: String,
        @Temporal(TemporalType.DATE)
        val date : Date = java.sql.Date.valueOf(LocalDate.now())
        ){
    constructor(antiqueDto: AntiqueDto,picId : UUID) : this(id = null,type = antiqueDto.type,name = antiqueDto.name,desp = antiqueDto.desp,verificationProcesses = mutableListOf<VerificationProcess>(),user = null,
            pic=picId,invalid = false,cert = null,wearAndTear = null,owner = null
    ,sourceInfo=antiqueDto.sourceInfo, spec=antiqueDto.spec, wholeness=antiqueDto.wholeness, era=antiqueDto.era, authType=antiqueDto.authType)
    constructor(antiqueDto: AntiqueDto,user : User,picId : UUID) : this(id = null,type = antiqueDto.type,name = antiqueDto.name,desp = antiqueDto.desp,verificationProcesses = mutableListOf<VerificationProcess>(),user = user,pic=picId,invalid = false,cert = null,wearAndTear = null,owner = null,sourceInfo=antiqueDto.sourceInfo, spec=antiqueDto.spec, wholeness=antiqueDto.wholeness, era=antiqueDto.era, authType=antiqueDto.authType)
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
        var wearAndTear: WearAndTear?,
        val owner : ApplierInfo?,
        val sourceInfo: String,
        val spec: String,
        val wholeness: String,
        val era:String,
        val authType: String
){
    constructor(antique: Antique) : this(antique.type,antique.name,antique.desp,antique.invalid,antique.verificationProcesses.size?:0,null,antique.user?.name,antique.id!!,wearAndTear=antique.wearAndTear,owner = antique.owner,sourceInfo=antique.sourceInfo, spec=antique.spec, wholeness=antique.wholeness, era=antique.era, authType=antique.authType)
}

