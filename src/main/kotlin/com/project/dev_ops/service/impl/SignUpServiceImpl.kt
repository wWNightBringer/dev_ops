package com.project.dev_ops.service.impl

import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.service.SignUpService
import com.project.dev_ops.service.TokenUserService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class SignUpServiceImpl(private val tokenService: TokenUserService) : SignUpService {

    override fun signUp(userVo: UserVo): ResponseEntity<UserVo> {
        TODO("Not yet implemented")
    }
}