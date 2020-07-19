package xyz.luchengeng.authentication.repo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import xyz.luchengeng.authentication.entity.Antique
import xyz.luchengeng.authentication.entity.AntiqueDto
import xyz.luchengeng.authentication.entity.VerificationProcess

interface AntiqueRepo : JpaRepository<Antique,Long> {
    fun findAllByUserId(id : Long,pageable : Pageable) : Page<Antique>
    @Query("select a from Antique a where not count(a.verificationProcesses) = 3")
    fun findAntiqueNeedsVerification(pageable : Pageable) : Page<Antique>
    @Query("select new xyz.luchengeng.authentication.entity.AntiqueDto(a) from Antique a where a.user.id = ?1",countQuery = "select count(a) from Antique a where a.user.id = ?1")
    fun findAllDtoByUserId(id : Long,pageable : Pageable) : Page<AntiqueDto>
    @Query("select new xyz.luchengeng.authentication.entity.AntiqueDto(a) from Antique a where not size(a.verificationProcesses) = 3 and a.invalid = false",countQuery = "select count(a) from Antique a where not a.verificationProcesses.size = 3")
    fun findAntiqueDtoNeedsVerification(pageable : Pageable) : Page<AntiqueDto>
    @Query("select a.pic  from Antique a where a.id = ?1")
    fun findPicById(id : Long) : ByteArray?
    @Query("select new xyz.luchengeng.authentication.entity.AntiqueDto(a) from Antique a where  a.id = ?1")
    fun findAntiqueDtoById(id : Long) : AntiqueDto?
    @Query("select new xyz.luchengeng.authentication.entity.AntiqueDto(a) from Antique a",countQuery = "select count(a) from Antique a")
    fun findAllDto(pageable : Pageable) : Page<AntiqueDto>
    @Query("select new xyz.luchengeng.authentication.entity.AntiqueDto(a) from Antique a where size(a.verificationProcesses) = ?1",countQuery = "select count(a) from Antique a where a.verificationProcesses.size = ?1")
    fun findAntiqueDtoAtVerificationStage(stage : Int,pageable : Pageable) : Page<AntiqueDto>
    @Query("select a.verificationProcesses  from Antique a where a.id = ?1")
    fun getVerificationsByAntiqueId(id : Long) : List<VerificationProcess>
    @Query("select new xyz.luchengeng.authentication.entity.AntiqueDto(a) from Antique a where a.name like %?1% or a.desp like %?1%",countQuery = "select count(a) from Antique a where a.name like %?1% or a.desp like %?1%")
    fun searchDto(keyWord : String,pageable : Pageable) : Page<AntiqueDto>
    @Query("select new xyz.luchengeng.authentication.entity.AntiqueDto(a) from Antique a where (a.name like %?1% or a.desp like %?1%) and a.user.id = ?2",countQuery = "select count(a) from Antique a where (a.name like %?1% or a.desp like %?1%) and a.user.id = ?2")
    fun searchDtoOfUserId(keyWord : String,userId : Long,pageable : Pageable) : Page<AntiqueDto>
}