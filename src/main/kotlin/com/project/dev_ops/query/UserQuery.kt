package com.project.dev_ops.query

import com.project.dev_ops.model.vo.UserVo

interface UserQuery {
    fun validateUsername(username: String): UserVo
}