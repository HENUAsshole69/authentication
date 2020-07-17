package xyz.luchengeng.authentication.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.luchengeng.authentication.service.LoggingService

@Aspect
@Component
class LoggingAspect @Autowired constructor(private val loggingService: LoggingService) {
    @Before("execution(* xyz.luchengeng.authentication.controller.*.*(..))")
    fun controllerLogging(joinPoint : JoinPoint){
        if(joinPoint.args[0] is String)loggingService.saveLog(joinPoint.args[0].toString(),joinPoint.signature.name)
    }
}