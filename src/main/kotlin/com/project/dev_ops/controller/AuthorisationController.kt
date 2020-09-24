package com.project.dev_ops.controller

import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.service.AuthorisationService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/authorisation"])
@RequiredArgsConstructor
class AuthorisationController(private val authorisationService: AuthorisationService) {

    @PostMapping(value = ["/sign-up"])
    fun signUp(@RequestBody @Validated userVo: UserVo): ResponseEntity<UserVo> {
        return authorisationService.signUp(userVo)
    }

    @PostMapping(value = ["/sign-in"])
    fun signIn(@RequestBody @Validated userVo: UserVo): ResponseEntity<UserVo> {
        return authorisationService.signIn(userVo)
    }
}