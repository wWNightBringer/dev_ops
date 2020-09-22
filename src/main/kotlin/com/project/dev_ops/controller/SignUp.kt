package com.project.dev_ops.controller

import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.service.SignUpService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/sign-up"])
@RequiredArgsConstructor
class SignUp(private val signUpService: SignUpService) {

    fun signUp(@RequestBody userVo: UserVo): ResponseEntity<UserVo> {
        return signUpService.signUp(userVo)
    }
}