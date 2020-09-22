package com.project.dev_ops.service.impl

import com.project.dev_ops.config.CipherConfig
import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.query.UserQuery
import com.project.dev_ops.service.SignUpService
import com.project.dev_ops.service.TokenUserService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
class SignUpServiceImpl(private val tokenService: TokenUserService,
                        private val userQuery: UserQuery,
                        private val cipherConfig: CipherConfig,
                        @Value(value = "dev_ops.userExist")
                        private val userExist: String) : SignUpService {

    override fun signUp(userVo: UserVo): ResponseEntity<UserVo> {
        cipherConfig.encode(userVo.password)
        val validateUser = userQuery.validateUser(userVo)
        if (validateUser.username != null) return ResponseEntity.notFound().eTag(userExist).build()

        return ResponseEntity.ok().body(UserVo())
    }
}