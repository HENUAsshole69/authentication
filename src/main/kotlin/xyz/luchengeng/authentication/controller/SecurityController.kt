package xyz.luchengeng.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.authentication.entity.Credential
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
}