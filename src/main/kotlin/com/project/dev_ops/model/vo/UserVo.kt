package com.project.dev_ops.model.vo

import java.io.Serializable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


class UserVo : Serializable {
    @NotNull
    @NotBlank
    var username: String? = null
    var password: String? = null
    var email: String? = null
    var roleId: Int? = null

    constructor(username: String?, password: String?, email: String?, roleId: Int?) {
        this.username = username
        this.password = password
        this.email = email
        this.roleId = roleId
    }

    constructor()


}