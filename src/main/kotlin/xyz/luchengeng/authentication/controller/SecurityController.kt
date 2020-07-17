package xyz.luchengeng.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.authentication.entity.Credential
import xyz.luchengeng.authentication.entity.User
import xyz.luchengeng.authentication.entity.UserType
import xyz.luchengeng.authentication.entity.VerificationProcessStage
import xyz.luchengeng.authentication.except.NotAuthorizedException
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.authentication.repo.UserRepo
import xyz.luchengeng.authentication.service.SecurityService

@RestController
class SecurityController @Autowired constructor(private val securityService: SecurityService) {
    @GetMapping("/login")
    fun login(@RequestParam userName : String,@RequestParam password : String) : String {
        return securityService.login(userName, password)
    }
    @PostMapping("/register")
    fun register(@RequestBody credential: Credential){
        securityService.register(credential)
    }
    @PutMapping("/user/verifiable/{userId}")
    fun updateVerificationAuth(@RequestHeader("x-api-key") jwt : String,@PathVariable userId : Long, @RequestBody verifiable : MutableList<VerificationProcessStage>){
        val user = securityService.auth("updateVerificationAuth",jwt)
        securityService.setVerificationAuthorization(userId,verifiable)
    }
    @GetMapping("/user/page/{pageNo}/{pageLen}")
    fun getAllUser(@RequestHeader("x-api-key") jwt : String,@PathVariable pageNo : Int,@PathVariable pageLen : Int): Page<User> {
        val user = securityService.auth("updateVerificationAuth",jwt)
        return securityService.getUserPage(PageRequest.of(pageNo,pageLen))
    }
}