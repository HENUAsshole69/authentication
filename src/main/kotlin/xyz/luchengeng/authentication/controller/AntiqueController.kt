package xyz.luchengeng.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.authentication.entity.Antique
import xyz.luchengeng.authentication.entity.AntiqueDto
import xyz.luchengeng.authentication.entity.UserType
import xyz.luchengeng.authentication.service.AntiqueService
import xyz.luchengeng.authentication.service.SecurityService

@RestController
class AntiqueController @Autowired constructor(private val antiqueService: AntiqueService,private val securityService: SecurityService) {
    @GetMapping("/antique/page/{pageNo}/{pageLen}")
    fun getAntique(@RequestHeader("x-api-key") jwt : String,@PathVariable pageNo : Int,@PathVariable pageLen : Int) : Page<AntiqueDto> {
        val user = securityService.auth("getAntique",jwt)
        return when(user.type){
            UserType.ADMIN,UserType.JUDICIAL_DEPT->{
                antiqueService.getAllAntique(pageNo, pageLen)
            }
            else->{
                antiqueService.getAntiqueOfUser(user,pageNo, pageLen)
            }
        }
    }
    @GetMapping("/antique/pic/{id}")
    fun getAntiquePic(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long) : ByteArray{
        val user = securityService.auth("getAntiquePic",jwt)
        return antiqueService.getAntiquePicById(id)
    }
    @PostMapping("/antique")
    fun postAntique(@RequestHeader("x-api-key") jwt : String,@RequestBody antique: Antique): Antique{
        val user = securityService.auth("postAntique",jwt)
        antique.verificationProcesses = mutableListOf()
        antique.user = user
        return antiqueService.newAntique(antique)
    }

    @GetMapping("/antique/{id}")
    fun getAntique(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long) : AntiqueDto{
        val user = securityService.auth("getAntique",jwt)
        return antiqueService.getAntiqueDtoById(id)
    }
}