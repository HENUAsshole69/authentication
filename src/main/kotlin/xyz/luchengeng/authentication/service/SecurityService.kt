package xyz.luchengeng.authentication.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.*
import xyz.luchengeng.authentication.except.NotAuthorizedException
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.authentication.repo.CredRepo
import xyz.luchengeng.authentication.repo.UserRepo
import java.awt.print.Pageable
import javax.annotation.PostConstruct


@Service
class SecurityService @Autowired constructor(private val authenticationService: AuthenticationService,private val authorizationService: AuthorizationService,private val credRepo: CredRepo,private val userRepo: UserRepo) {
    @PostConstruct
    private fun createAdminUser(){
        if(userRepo.count() == 0L){
            credRepo.save(Credential(null,User(id=null,type = UserType.ADMIN,name = "admin",verificationProcesses = mutableListOf(), info = UserInfo(null,"","admin"),verifiable = mutableListOf()),"admin"))
        }
    }
    fun auth(obj : String,token : String) : User{
        val user = authenticationService.tokenVerify(token)
        authorizationService(obj, user)
        return user
    }
    fun login(userName : String,password : String) : String{
        val user = credRepo.findFirstByUserNameAndPassword(userName, password)?:throw NotAuthorizedException("UserName/Password Error")
        return authenticationService.tokenCreate(user.user)
    }

    fun register(cred : Credential) : Credential {
        cred.user.verifiable.clear()
        return credRepo.save(cred)
    }


    fun setVerificationAuthorization(userId : Long,verifiable : MutableList<VerificationProcessStage>){
        val user = userRepo.findByIdOrNull(userId)?:throw NotFoundException("User Not Found")
        user.verifiable.clear()
        for(ver in verifiable){
            user.verifiable.add(ver)
        }
        userRepo.save(user)
    }

    fun setUserRole(userId : Long,type : UserType){
        val user = userRepo.findByIdOrNull(userId)?:throw NotFoundException("User Not Found")
        user.type = type
        userRepo.save(user)
    }

    fun getUserPage(pageable: org.springframework.data.domain.Pageable) =
        userRepo.findAll(
                pageable
        )

    fun searchUser(keyWord : String,pageable: org.springframework.data.domain.Pageable) =
            userRepo.findUserByName(keyWord, pageable)

    fun delUser(userId : Long){
            credRepo.delUserById(userId)
    }

    fun updatePassword(userId : Long,password: String){
        credRepo.updatePassword(userId, password)
    }
}