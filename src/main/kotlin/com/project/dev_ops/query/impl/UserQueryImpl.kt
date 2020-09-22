package com.project.dev_ops.query.impl

import com.project.dev_ops.model.entity.User
import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.query.UserQuery
import com.project.dev_ops.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserQueryImpl(private val userRepository: UserRepository) : UserQuery {
    override fun validateUser(userVo: UserVo): UserVo {
        val findUserByUsername = userRepository.findUserByUsername(userVo.username) ?: return UserVo()
        return mapUserToUserVo(findUserByUsername)
    }

    fun mapUserToUserVo(user: User): UserVo {
        return UserVo(user.username, user.password, user.email)
    }


}