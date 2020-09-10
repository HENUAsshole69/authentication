package xyz.luchengeng.authentication.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.*
import xyz.luchengeng.authentication.except.NotAuthorizedException
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.authentication.repo.AntiqueRepo
import xyz.luchengeng.authentication.repo.CredRepo
import xyz.luchengeng.authentication.repo.UserRepo
import xyz.luchengeng.authentication.repo.VerRepo
import java.awt.print.Pageable
import javax.annotation.PostConstruct


@Service
class SecurityService @Autowired constructor(private val verRepo: VerRepo,private val antiqueRepo: AntiqueRepo,private val authenticationService: AuthenticationService,private val authorizationService: AuthorizationService,private val credRepo: CredRepo,private val userRepo: UserRepo) {
    @PostConstruct
    private fun createAdminUser(){
        if(userRepo.count() == 0L){
            credRepo.save(Credential(null,User(id=null,type = UserType.ADMIN,name = "admin",verificationProcesses = mutableListOf(), verifiable = mutableListOf()),"admin"))
            credRepo.save(Credential(null,User(id=null,type = UserType.ARCH_DEPT,name = "考古部门用户",verificationProcesses = mutableListOf(),verifiable = mutableListOf()),"123456"))
            credRepo.save(Credential(null,User(id=null,type = UserType.JUDICIAL_DEPT,name = "司法部门用户",verificationProcesses = mutableListOf(),verifiable = mutableListOf()),"123456"))
            credRepo.save(Credential(null,User(id=null,type = UserType.ANTIQUE_STORE,name = "文物商店用户",verificationProcesses = mutableListOf(),verifiable = mutableListOf()),"123456"))
            credRepo.save(Credential(null,User(id=null,type = UserType.AUCTIONEER,name = "拍卖行用户",verificationProcesses = mutableListOf(), verifiable = mutableListOf()),"123456"))
            credRepo.save(Credential(null,User(id=null,type = UserType.AUTH_CENTER,name = "认证中心用户",verificationProcesses = mutableListOf(),verifiable = mutableListOf()),"123456"))
            credRepo.save(Credential(null,User(id=null,type = UserType.FINANCE,name = "财务部门用户",verificationProcesses = mutableListOf(),verifiable = mutableListOf()),"123456"))

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
            val cred = credRepo.findCredByUserId(userId)?:throw NotFoundException("Credential Not Found")
            val antiques = antiqueRepo.findAllByUserId(userId, org.springframework.data.domain.Pageable.unpaged())
            antiques.content.forEach { antique ->
                antique.verificationProcesses.forEach {
                    verRepo.deleteById(it.id!!)
                }
            }
            antiqueRepo.deleteAll(antiques.content)
            credRepo.delete(cred)
            userRepo.delete(cred.user)
    }

    fun updatePassword(userId : Long,password: String){
        val cred = credRepo.findCredByUserId(userId)?:throw NotFoundException("Credential Not Found")
        cred.password = password
        credRepo.save(cred)
    }
}