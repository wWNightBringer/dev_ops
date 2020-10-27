package com.project.dev_ops.service.impl

import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.service.TokenUserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenUserServiceImpl(private val userDetailsService: UserDetailsService,
                           @Value(value = "security.code")
                           private val key: String) : TokenUserService {
    private val usernameAnonymous = "Anonymous"
    private val logger = LoggerFactory.getLogger(TokenUserServiceImpl::class.java)

    override fun createToken(userVo: UserVo): String {
        val user: User = if (userVo.username == null) {
            userVo.username = usernameAnonymous
            userDetailsService.loadUserByUsername(usernameAnonymous) as User
        } else userDetailsService.loadUserByUsername(userVo.username) as User

        val tokenData = hashMapOf<String, Any>()
        tokenData["username"] = user.username
        tokenData["token_create_date"] = Date().time

        return Jwts.builder()
                .setClaims(tokenData)
                .setSubject(user.username)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact()
    }

    override fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token)
            return true
        } catch (signatureEx: SignatureException) {
            logger.error("Invalid signature")
        } catch (e: Exception) {
            logger.error("Invalid token")
        }
        return false
    }

    override fun getUsernameFromToken(token: String): String {
        val body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).body
        return body.subject
    }
}