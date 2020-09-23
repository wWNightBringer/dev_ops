package com.project.dev_ops.query.impl

import com.project.dev_ops.model.entity.User
import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.query.UserQuery
import com.project.dev_ops.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserQueryImpl(private val userRepository: UserRepository) : UserQuery {

    override fun validateUsername(username: String): UserVo {
        val user = userRepository.findUserByUsername(username) ?: return UserVo()
        return mapUserToUserVo(user.orElse(User(null, null, null, null, null, null))!!)
    }

    fun mapUserToUserVo(user: User): UserVo {
        return UserVo(user.username, user.password, user.email)
    }


}