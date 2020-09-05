package xyz.luchengeng.authentication.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.*
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.authentication.repo.AntiqueRepo
import xyz.luchengeng.authentication.repo.UserRepo
import xyz.luchengeng.authentication.repo.VerRepo

@Service
class VerService(private val userRepo: UserRepo,private val antiqueRepo: AntiqueRepo,private val verRepo: VerRepo) {
    fun userVerification(userId : Long,verificationProcess: VerificationProcess){
       val user = userRepo.findByIdOrNull(userId)?:throw NotFoundException("User Not Found")
       user.verificationProcesses.add(verificationProcess)
        userRepo.save(user)
    }


    fun getStageForUser(userId: Long) : VerificationProcessStage?{
        val user = userRepo.findByIdOrNull(userId)?:throw NotFoundException("User Not Found")
        return getLatestStage(user.verificationProcesses)
    }

    fun getStageForAntique(antiqueId: Long) : VerificationProcessStage?{
        val a = antiqueRepo.findByIdOrNull(antiqueId)?:throw NotFoundException("Antique Not Found")
        return getLatestStage(a.verificationProcesses?: mutableListOf())
    }

    fun verifyAntique(user : User,antiqueId: Long,verificationProcessDto: VerificationProcessDto){
        val antique = antiqueRepo.findByIdOrNull(antiqueId)?:throw NotFoundException("Antique Not Found")
        val a = verificationProcessDto.toEntity(user)
        antique.verificationProcesses.add(verRepo.save(verificationProcessDto.toEntity(user)))
        antiqueRepo.save(antique)
    }

    private fun getLatestStage(processes : List<VerificationProcess>) : VerificationProcessStage?{
        processes.forEach{
            if(it.stage == VerificationProcessStage.STAGE_THREE){
                return VerificationProcessStage.STAGE_THREE
            }
        }
        processes.forEach{
            if(it.stage == VerificationProcessStage.STAGE_TWO){
                return VerificationProcessStage.STAGE_TWO
            }
        }
        processes.forEach{
            if(it.stage == VerificationProcessStage.STAGE_ONE){
                return VerificationProcessStage.STAGE_ONE
            }
        }
        return null
    }
}