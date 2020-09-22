package com.project.dev_ops.repository

import com.project.dev_ops.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findUserByUsername(username: String?): User?
}