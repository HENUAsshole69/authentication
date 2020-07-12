package xyz.luchengeng.authentication.service

import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.User
import xyz.luchengeng.authentication.except.NotAuthorizedException

@Service
class AuthorizationService {
    operator fun invoke(obj : String,user : User){
        if(false) throw NotAuthorizedException("Forbidden")
    }
}