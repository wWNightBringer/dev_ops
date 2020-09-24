package com.project.dev_ops.query.impl

import com.project.dev_ops.model.entity.User
import com.project.dev_ops.model.vo.UserVo
import com.project.dev_ops.query.UserQuery
import com.project.dev_ops.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserQueryImpl(private val userRepository: UserRepository) : UserQuery {

    @Transactional(readOnly = true)
    override fun validateUsername(username: String): UserVo {
        val user = userRepository.findUserByUsername(username) ?: return UserVo()
        return mapUserToUserVo(user.orElse(User(null, null, null, null, null, null))!!)
    }

    @Transactional
    override fun saveUser(userVo: UserVo) {
        userRepository.saveAndFlush(mapUserVoToUser((userVo)))
    }

    private fun mapUserVoToUser(userVo: UserVo): User {
        return User(null, userVo.username, userVo.password, userVo.email, null, userVo.roleId)
    }

    private fun mapUserToUserVo(user: User): UserVo {
        return UserVo(user.username, user.password, user.email, user.roleId)
    }


}