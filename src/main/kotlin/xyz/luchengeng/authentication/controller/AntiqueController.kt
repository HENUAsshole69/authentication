package xyz.luchengeng.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.authentication.entity.Antique
import xyz.luchengeng.authentication.entity.AntiqueDto
import xyz.luchengeng.authentication.entity.UserType
import xyz.luchengeng.authentication.repo.AntiqueRepo
import xyz.luchengeng.authentication.service.AntiqueService
import xyz.luchengeng.authentication.service.SecurityService

@RestController
class AntiqueController @Autowired constructor(private val antiqueService: AntiqueService,private val securityService: SecurityService,private val antiqueRepo: AntiqueRepo) {
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
    fun postAntique(@RequestHeader("x-api-key") jwt : String,@RequestBody antique: AntiqueDto): AntiqueDto{
        val user = securityService.auth("postAntique",jwt)

        return AntiqueDto(antiqueService.newAntique(Antique(antique,user)))
    }

    @GetMapping("/antique/{id}")
    fun getAntique(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long) : AntiqueDto{
        val user = securityService.auth("getAntique",jwt)
        return antiqueService.getAntiqueDtoById(id)
    }
    @GetMapping("/antique/search/page/{pageNo}/{pageLen}")
    fun searchAntique(@RequestHeader("x-api-key") jwt : String,@PathVariable pageNo : Int,@PathVariable pageLen : Int,@RequestParam key : String): Page<AntiqueDto>{
        val user = securityService.auth("searchAntique",jwt)
        return antiqueRepo.searchDto(keyWord = key,pageable = PageRequest.of(pageNo,pageLen))
    }
    @GetMapping("/user")
    fun getUserObj(@RequestHeader("x-api-key") jwt : String)=
        securityService.auth("searchAntique",jwt)

}