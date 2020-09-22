package com.project.dev_ops.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class User(
        @Id
        val id: Int,
        val username: String,
        val password: String,
        val email: String,
        @Column(name = "shop_id")
        val shopId: Int,
        @Column(name = "role_id")
        val roleId: Int
)
