package xyz.luchengeng.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.authentication.entity.*
import xyz.luchengeng.authentication.repo.AntiqueRepo
import xyz.luchengeng.authentication.service.AntiqueService
import xyz.luchengeng.authentication.service.InventoryService
import xyz.luchengeng.authentication.service.SecurityService
import java.time.LocalDate

@RestController
class AntiqueController @Autowired constructor(private val antiqueService: AntiqueService,private val securityService: SecurityService,private val antiqueRepo: AntiqueRepo,private val inventoryService: InventoryService) {
    private fun isGlobalVisibleForUserType(type: UserType) =
         type == UserType.FINANCE ||
                type == UserType.AUTH_CENTER ||
                type == UserType.ADMIN ||
                type == UserType.JUDICIAL_DEPT ||
                type == UserType.ARCH_DEPT
    @GetMapping("/antique/page/{pageNo}/{pageLen}")
    fun getAntique(@RequestHeader("x-api-key") jwt : String,
                   @PathVariable pageNo : Int,
                   @PathVariable pageLen : Int,
                   @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) from : LocalDate,
                   @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)  to: LocalDate
                   ) : Page<AntiqueDto> {
        val user = securityService.auth("getAntique",jwt)
        return when{
            isGlobalVisibleForUserType(user.type)->{
                antiqueService.getAllAntique(pageNo, pageLen,from, to)
            }
            else->{
                antiqueService.getAntiqueOfUser(user,pageNo, pageLen,from, to)
            }
        }
    }
    @PostMapping("/antique/cert/{id}")
    fun postCertForAntique(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long, @RequestBody cert : ByteArray){
        val user = securityService.auth("postCertForAntique",jwt)
        antiqueService.saveCertForAntique(id,cert)
    }

    @GetMapping("/antique/cert/{id}")
    fun getAntiqueCert(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long) : ByteArray{
        val user = securityService.auth("getAntiqueCert",jwt)
        return antiqueService.getCertForAntique(id)
    }

    @GetMapping("/antique/pic/{id}")
    fun getAntiquePic(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long) : ByteArray{
        val user = securityService.auth("getAntiquePic",jwt)
        return antiqueService.getAntiquePicById(id)
    }
    @PostMapping("/antique")
    fun postAntique(@RequestHeader("x-api-key") jwt : String,@RequestBody antique: AntiqueDto): AntiqueDto{
        val user = securityService.auth("postAntique",jwt)
        return AntiqueDto(antiqueService.newAntique(antique))
    }
    @PostMapping("/antique/{id}/owner/individual")
    fun postAntiqueIndividualOwner(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long,@RequestBody applierInfo : IndividualApplierInfo){
        val user = securityService.auth("postAntiqueIndividualOwner",jwt)
        antiqueService.setAntiqueOwner(id,applierInfo)
    }

    @PostMapping("/antique/{id}/owner/enterprise")
    fun postAntiqueEnterpriseOwner(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long,@RequestBody applierInfo : EnterpriseApplierInfo){
        val user = securityService.auth("postAntiqueEnterpriseOwner",jwt)
        antiqueService.setAntiqueOwner(id,applierInfo)
    }

    @GetMapping("/antique/{id}")
    fun getAntique(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long) : AntiqueDto{
        val user = securityService.auth("getAntique",jwt)
        return antiqueService.getAntiqueDtoById(id)
    }
    @GetMapping("/antique/search/page/{pageNo}/{pageLen}")
    fun searchAntique(@RequestHeader("x-api-key") jwt : String,
                      @PathVariable pageNo : Int,
                      @PathVariable pageLen : Int,
                      @RequestParam key : String,
                      @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) from : LocalDate,
                      @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)  to: LocalDate): Page<AntiqueDto>{
        val user = securityService.auth("searchAntique",jwt)
        return when{
            isGlobalVisibleForUserType(user.type)->
                antiqueRepo.searchDto(key,java.sql.Date.valueOf(from),java.sql.Date.valueOf(to), PageRequest.of(pageNo,pageLen))
            else->
                antiqueRepo.searchDtoOfUserId(key,user.id!!,java.sql.Date.valueOf(from),java.sql.Date.valueOf(to),PageRequest.of(pageNo,pageLen))
        }
    }


    @PostMapping("/antique/wearAndTear/{id}")
    fun postWearAndTearForAntique(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long, @RequestBody wearAndTear: WearAndTear){
        val user = securityService.auth("postWearAndTearForAntique",jwt)
        antiqueService.saveWearAndTear(id,wearAndTear)
    }
    @PostMapping("/antique/{id}/inventory")
    fun postAntiqueInventory(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long,@RequestBody inventoryDto: InventoryDto){
        val user = securityService.auth("postAntiqueInventory",jwt)
        inventoryService.setInventoryForId(id, inventoryDto)
    }
    @GetMapping("/antique/{id}/inventory")
    fun getAntiqueInventory(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long) : InventoryDto{
        val user = securityService.auth("getAntiqueInventory",jwt)
        return inventoryService.findDtoByAntiqueId(id)
    }
    @GetMapping("/antique/{id}/inventory/file")
    fun getAntiqueInventoryFileList(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long): List<File>{
        val user = securityService.auth("getAntiqueInventoryFileList",jwt)
        return inventoryService.findFileForAntiqueInventory(id)
    }

    @DeleteMapping("/antique/{antiqueId}/inventory/file/{fileId}")
    fun delFile(@PathVariable antiqueId: Long,@PathVariable fileId : Long){
        inventoryService.delFileForAntiqueInventory(antiqueId,fileId)
    }
}