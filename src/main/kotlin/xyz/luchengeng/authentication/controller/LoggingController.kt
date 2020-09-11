package xyz.luchengeng.authentication.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.authentication.entity.ApiPageRequest
import xyz.luchengeng.authentication.entity.LoggingEntryDto
import xyz.luchengeng.authentication.service.LoggingService
import xyz.luchengeng.authentication.service.SecurityService

@RestController
class LoggingController @Autowired constructor(private val securityService: SecurityService, private val loggingService: LoggingService) {
    @GetMapping("/log/page/{pageNo}/{pageLen}")
    fun getLogPage(@RequestHeader("x-api-key") jwt : String, @PathVariable pageNo : Int, @PathVariable pageLen : Int): Page<LoggingEntryDto> {
        val user = securityService.auth("getLogPage",jwt)
        return loggingService.getLoggingPage(pageNo,pageLen)
    }
    @PostMapping("/log/page")
    fun getLogPage(@RequestHeader("x-api-key") jwt : String,@RequestBody pageable: ApiPageRequest): Page<LoggingEntryDto> {
        val user = securityService.auth("getLogPage",jwt)
        return loggingService.getLoggingPage(pageable.toPageRequest())
    }
}