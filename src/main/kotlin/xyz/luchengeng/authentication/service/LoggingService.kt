package xyz.luchengeng.authentication.service

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.LoggingEntry
import xyz.luchengeng.authentication.entity.LoggingEntryDto
import xyz.luchengeng.authentication.repo.LoggingRepo
import java.time.LocalDateTime

@Service
class LoggingService constructor(@Value("#{\${opNames}}") private val opNameMap : Map<String,String>,private val loggingRepo: LoggingRepo){
    fun saveLog(token : String,methodName : String){
        if(opNameMap.containsKey(methodName))
            loggingRepo.save(LoggingEntry(null,token,methodName, LocalDateTime.now()))
    }
    fun getLoggingPage(pageNo : Int,pageLen : Int) : Page<LoggingEntryDto>{
        val page = loggingRepo.findAll(PageRequest.of(pageNo,pageLen))
        val dtoList = page.content.map {
            LoggingEntryDto(userName = userNameFromToken(it.token), opName = it.methodName, dateTime = it.dateTime)
        }
        return PageImpl(dtoList,PageRequest.of(pageNo,pageLen),page.totalElements)
    }

    private fun userNameFromToken(token : String) : String? =
            try {
            val decoded = JWT.decode(token)
            decoded.getClaim("name").asString()
        }catch (e : JWTDecodeException){
            null
        }
}