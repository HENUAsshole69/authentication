package xyz.luchengeng.authentication.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import xyz.luchengeng.authentication.entity.User
import xyz.luchengeng.authentication.except.NotAuthorizedException
import xyz.luchengeng.authentication.repo.UserRepo

@Service
class AuthenticationService @Autowired constructor(private val userRepo: UserRepo, @Value("\${HMAC256.secret}")
private val secret: String) {

    fun tokenCreate(subject: User): String {
        val algorithm = Algorithm.HMAC256(secret)
        return JWT.create()
                .withSubject(subject.id.toString())
                .withClaim("name", subject.name)
                .withClaim("role", subject.type.toString())
                .sign(algorithm)
    }

    fun tokenVerify(token: String?): User {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            val verifier = JWT.require(algorithm)
                    .build()
            val jwt = verifier.verify(token)
            userRepo.findById(jwt.subject.toLong()).orElseThrow { NotAuthorizedException("User Not Found") }
        } catch (exception: JWTVerificationException) {
            throw NotAuthorizedException("JWT verification exception")
        }
    }
}