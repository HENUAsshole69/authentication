package xyz.luchengeng.authentication.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.Credential
import xyz.luchengeng.authentication.entity.User
import xyz.luchengeng.authentication.except.NotAuthorizedException
import xyz.luchengeng.authentication.repo.CredRepo


@Service
class SecurityService @Autowired constructor(private val authenticationService: AuthenticationService,private val authorizationService: AuthorizationService,private val credRepo: CredRepo) {
    fun auth(obj : String,token : String) : User{
        val user = authenticationService.tokenVerify(token)
        authorizationService(obj, user)
        return user
    }
    fun login(userName : String,password : String) : String{
        val user = credRepo.findFirstByUserNameAndPassword(userName, password)?:throw NotAuthorizedException("UserName/Password Error")
        return authenticationService.tokenCreate(user.user)
    }

    fun register(cred : Credential) : Credential =
        credRepo.save(cred)

}