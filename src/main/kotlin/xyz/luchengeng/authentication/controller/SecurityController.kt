package xyz.luchengeng.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.authentication.entity.*
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


    @GetMapping("/user")
    fun getUserObj(@RequestHeader("x-api-key") jwt : String)=
            securityService.auth("getUserObj",jwt)


    @PutMapping("/user/verifiable/{userId}")
    fun updateVerificationAuth(@RequestHeader("x-api-key") jwt : String,@PathVariable userId : Long, @RequestBody verifiable : MutableList<VerificationProcessStage>){
        val user = securityService.auth("updateVerificationAuth",jwt)
        securityService.setVerificationAuthorization(userId,verifiable)
    }
    @PutMapping("/user/type/{userId}")
    fun updateType(@RequestHeader("x-api-key") jwt : String,@PathVariable userId : Long, @RequestParam type : UserType){
        val user = securityService.auth("updateType",jwt)
        securityService.setUserRole(userId, type)
    }
    @GetMapping("/user/page/{pageNo}/{pageLen}")
    fun getAllUser(@RequestHeader("x-api-key") jwt : String,@PathVariable pageNo : Int,@PathVariable pageLen : Int): Page<User> {
        val user = securityService.auth("getAllUser",jwt)
        return securityService.getUserPage(PageRequest.of(pageNo,pageLen))
    }
    @GetMapping("/user/search/page/{pageNo}/{pageLen}")
    fun searchUser(@RequestHeader("x-api-key") jwt : String,@PathVariable pageNo : Int,@PathVariable pageLen : Int,@RequestParam key : String): Page<User>{
        val user = securityService.auth("searchUser",jwt)
        return securityService.searchUser(key,PageRequest.of(pageNo,pageLen))
    }
    @DeleteMapping("/user/{id}")
    fun delUser(@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long){
        val user = securityService.auth("delUser",jwt)
        securityService.delUser(id)
    }
    @PutMapping("/user/password/{userId}")
    fun updatePassword(@RequestHeader("x-api-key") jwt : String,@PathVariable userId : Long, @RequestParam password : String){
        val user = securityService.auth("updatePassword",jwt)
        when{
            user.type === UserType.ADMIN->{
                securityService.updatePassword(userId, password)
            }
            else->{
                if(userId != user.id) throw NotAuthorizedException("non admin user can only mod its own password")
                securityService.updatePassword(userId, password)
            }
        }
    }

}