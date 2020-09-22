package com.project.dev_ops.model.vo


class UserVo {
    var username: String? = null
    var password: String? = null
    var email: String? = null

    constructor(username: String?, password: String?, email: String?) {
        this.username = username
        this.password = password
        this.email = email
    }

    constructor()


}