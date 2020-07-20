package xyz.luchengeng.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.authentication.entity.AntiqueDto
import xyz.luchengeng.authentication.entity.VerificationProcess
import xyz.luchengeng.authentication.entity.VerificationProcessDto
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.authentication.repo.AntiqueRepo
import xyz.luchengeng.authentication.service.AntiqueService
import xyz.luchengeng.authentication.service.SecurityService
import xyz.luchengeng.authentication.service.VerService

@RestController
class VerController @Autowired constructor(private val antiqueService: AntiqueService,
                                           private val antiqueRepo: AntiqueRepo,
private val verService: VerService,
private val securityService: SecurityService) {
    @GetMapping("/antique/needVerification/page/{pageNo}/{pageLen}")
    fun getAntiqueNeedVerification(@RequestHeader("x-api-key") jwt : String, @PathVariable pageNo : Int, @PathVariable pageLen : Int) : Page<AntiqueDto>{
        val user = securityService.auth("getAntiqueNeedVerification",jwt)
        return antiqueService.getAntiqueNeedVerification(pageNo,pageLen)
    }

    @GetMapping("/antique/atVerificationStage/{stage}/page/{pageNo}/{pageLen}")
    fun getAntiqueAtStage(@RequestHeader("x-api-key") jwt : String, @PathVariable stage : Int, @PathVariable pageNo : Int, @PathVariable pageLen : Int) : Page<AntiqueDto>{
        val user = securityService.auth("getAntiqueAtStage",jwt)
        return antiqueService.getAntiqueAtStage(stage,pageNo,pageLen)
    }

    @PostMapping("/verify/antique/{antiqueId}")
    fun verifyAntique(@RequestHeader("x-api-key") jwt : String, @PathVariable antiqueId : Long,@RequestBody ver : VerificationProcessDto){
        val user = securityService.auth("verifyAntique",jwt)
        verService.verifyAntique(user,antiqueId,ver)
    }
    @GetMapping("/antique/verification/{antiqueId}")
    fun getVerificationsByAntiqueId(@RequestHeader("x-api-key") jwt : String, @PathVariable antiqueId : Long) : List<VerificationProcessDto>{
        val user = securityService.auth("getVerificationsByAntiqueId",jwt)
        return antiqueService.getVerificationsByAntiqueId(antiqueId).map { it.toDto() }
    }
    @DeleteMapping("/antique/verification/{antiqueId}")
    fun invalidateByAntiqueId(@RequestHeader("x-api-key") jwt : String, @PathVariable antiqueId : Long){
        val user = securityService.auth("invalidateByAntiqueId",jwt)
        val antique = antiqueRepo.findByIdOrNull(antiqueId)?:throw NotFoundException("Antique Not Found")
        antique.invalid = true
        antiqueRepo.save(antique)
    }
}