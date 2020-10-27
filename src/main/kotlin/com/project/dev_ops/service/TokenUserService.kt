package com.project.dev_ops.service

import com.project.dev_ops.model.vo.UserVo

interface TokenUserService {
    fun createToken(userVo: UserVo): String
    fun validateToken(token: String): Boolean
    fun getUsernameFromToken(token: String): String
}
