package com.project.dev_ops.service.impl

import com.project.dev_ops.config.CipherConfig
import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.query.UserQuery
import com.project.dev_ops.service.AuthorisationService
import com.project.dev_ops.service.TokenUserService
import com.project.dev_ops.util.HeaderUtil
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
class AuthorisationServiceImpl(private val tokenService: TokenUserService,
                               private val userQuery: UserQuery,
                               private val cipherConfig: CipherConfig,
                               @Value(value = "\${dev_ops.userExist}")
                               private val userExist: String,
                               @Value(value = "\${dev_ops.incorrectPassword}")
                               private val incorrectPassword: String) : AuthorisationService {

    override fun signUp(userVo: UserVo): ResponseEntity<UserVo> {
        /*val validateUser = userQuery.validateUsername(userVo.username!!)
        if (validateUser.username == null) return ResponseEntity.notFound().eTag(userExist).build()*/
        userVo.password = cipherConfig.base64Encode(userVo.password)
        userVo.roleId = 3
        userQuery.saveUser(userVo)

        return ResponseEntity.ok().body(UserVo())
    }

    override fun signIn(userVo: UserVo): ResponseEntity<UserVo> {
        val validate = userQuery.validateUsername(userVo.username!!)
        if (!validate.password.equals(cipherConfig.base64Encode(userVo.password)))
            return ResponseEntity.badRequest().eTag(incorrectPassword).build()
        val token = tokenService.createToken(userVo)
        validate.password = null

        return ResponseEntity(validate, HeaderUtil.createHttpHeaders(token), HttpStatus.OK)
    }
}