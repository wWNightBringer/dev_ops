package com.project.dev_ops.service

import com.project.dev_ops.model.vo.UserVo
import org.springframework.http.ResponseEntity


interface SignUpService {
    fun signUp(userVo: UserVo): ResponseEntity<UserVo>

}
