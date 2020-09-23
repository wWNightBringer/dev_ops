package com.project.dev_ops.service.impl

import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.service.TokenUserService
import org.springframework.stereotype.Service

@Service
class TokenUserServiceImpl : TokenUserService {

    fun createToken(userVo: UserVo): String {
        return ""
    }

    fun validateToken(): Boolean {
        return true
    }
}