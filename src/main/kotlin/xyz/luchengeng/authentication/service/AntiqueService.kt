package xyz.luchengeng.authentication.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.*
import xyz.luchengeng.authentication.except.BadRequestException
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.authentication.repo.AntiqueRepo
import java.time.LocalDate
import javax.print.DocFlavor

@Service
class AntiqueService @Autowired constructor(private val antiqueRepo: AntiqueRepo,private val contentService: ContentService) {

    private fun modByAntiqueId(id : Long,action : (Antique)->Unit){
        val antique = antiqueRepo.findByIdOrNull(id) ?: throw NotFoundException("Obj Not Found")
        action(antique)
        antiqueRepo.save(antique)
    }

    fun getAntiqueOfUser(user : User,pageNo : Int,pageLen : Int,from: LocalDate,to : LocalDate) : Page<AntiqueDto>{
        return antiqueRepo.findAllDtoByUserId(user.id!!,java.sql.Date.valueOf(from),java.sql.Date.valueOf(to),PageRequest.of(pageNo,pageLen))
    }

    fun getAllAntique(pageNo : Int,pageLen : Int,from: LocalDate,to : LocalDate) : Page<AntiqueDto>{
        return antiqueRepo.findAllDto(java.sql.Date.valueOf(from),java.sql.Date.valueOf(to),PageRequest.of(pageNo,pageLen))
    }

    fun newAntique(antique: AntiqueDto,user: User) =
        antiqueRepo.save(Antique(antique,user,contentService.saveContent(antique.pic?:throw BadRequestException("Antique contains no pinc"))))


    fun setAntiqueOwner(id : Long,info : ApplierInfo){
        modByAntiqueId(id){
            it.owner = info
        }
    }

    fun getAntiquePicById(id : Long) : ByteArray =
        contentService.getPic( antiqueRepo.findPicById(id)?:throw NotFoundException("Pic Not Found"))


    fun getAntiqueDtoById(id : Long) : AntiqueDto = antiqueRepo.findAntiqueDtoById(id)?:throw NotFoundException("Obj Not Found")

    fun getAntiqueNeedVerification(pageNo : Int,pageLen : Int): Page<AntiqueDto>{
        return antiqueRepo.findAntiqueDtoNeedsVerification(PageRequest.of(pageNo,pageLen))
    }

    fun getAntiqueAtStage(stage : Int,pageNo : Int,pageLen : Int): Page<AntiqueDto>{
        return antiqueRepo.findAntiqueDtoAtVerificationStage(stage,PageRequest.of(pageNo,pageLen))
    }

    fun getVerificationsByAntiqueId(antiqueId : Long)=
        antiqueRepo.getVerificationsByAntiqueId(antiqueId)

    fun saveCertForAntique(antiqueId: Long,cert:ByteArray) {
        modByAntiqueId(antiqueId){
            it.cert = contentService.saveContent(cert)
        }
    }

    fun getCertForAntique(antiqueId: Long) : ByteArray{
        val antique = antiqueRepo.findByIdOrNull(antiqueId) ?: throw NotFoundException("Obj Not Found")
        return contentService.getPic(antique.cert?: throw NotFoundException("Cert Not Found"))
    }

    fun saveWearAndTear(antiqueId: Long,wearAndTear: WearAndTear){
        modByAntiqueId(antiqueId){
            it.wearAndTear = wearAndTear
        }
    }
}