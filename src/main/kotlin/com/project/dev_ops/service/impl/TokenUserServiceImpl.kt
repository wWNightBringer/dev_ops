package com.project.dev_ops.service.impl

import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.service.TokenUserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
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

    override fun createToken(userVo: UserVo): String {
        val user: User = if (userVo.username == null) {
            userVo.username = usernameAnonymous
            userDetailsService.loadUserByUsername(usernameAnonymous) as User
        } else userDetailsService.loadUserByUsername(userVo.username) as User

        val tokenData = hashMapOf<String, Any>()
        tokenData["username"] = user.username
        tokenData["token_create_date"] = Date().time
        val jwtBuilder = Jwts.builder()
        jwtBuilder.addClaims(tokenData)
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact()
    }

    override fun validateToken(token: String): Boolean {
        return true
    }
}