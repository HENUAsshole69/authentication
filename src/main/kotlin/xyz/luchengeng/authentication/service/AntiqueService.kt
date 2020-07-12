package xyz.luchengeng.authentication.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.Antique
import xyz.luchengeng.authentication.entity.AntiqueDto
import xyz.luchengeng.authentication.entity.User
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.authentication.repo.AntiqueRepo
@Service
class AntiqueService @Autowired constructor(private val antiqueRepo: AntiqueRepo) {
    fun getAntiqueOfUser(user : User,pageNo : Int,pageLen : Int) : Page<AntiqueDto>{
        return antiqueRepo.findAllDtoByUserId(user.id!!,PageRequest.of(pageNo,pageLen))
    }

    fun getAllAntique(pageNo : Int,pageLen : Int) : Page<AntiqueDto>{
        return antiqueRepo.findAllDto(PageRequest.of(pageNo,pageLen))
    }

    fun newAntique(antique: Antique) = antiqueRepo.save(antique)

    fun getAntiquePicById(id : Long) : ByteArray = antiqueRepo.findPicById(id)?:throw NotFoundException("Pic Not Found")

    fun getAntiqueDtoById(id : Long) : AntiqueDto = antiqueRepo.findAntiqueDtoById(id)?:throw NotFoundException("Obj Not Found")

    fun getAntiqueNeedVerification(pageNo : Int,pageLen : Int): Page<AntiqueDto>{
        return antiqueRepo.findAntiqueDtoNeedsVerification(PageRequest.of(pageNo,pageLen))
    }

    fun getAntiqueAtStage(stage : Int,pageNo : Int,pageLen : Int): Page<AntiqueDto>{
        return antiqueRepo.findAntiqueDtoAtVerificationStage(stage,PageRequest.of(pageNo,pageLen))
    }

    fun getVerificationsByAntiqueId(antiqueId : Long)=
        antiqueRepo.getVerificationsByAntiqueId(antiqueId)

}