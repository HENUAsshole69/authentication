package xyz.luchengeng.authentication.except

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class NotAuthorizedException(message : String) : Exception(message)