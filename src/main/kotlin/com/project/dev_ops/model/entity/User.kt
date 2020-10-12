package com.project.dev_ops.model.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?,
        val username: String?,
        val password: String?,
        val email: String?,
        @Column(name = "shop_id")
        val shopId: Int?,
        @Column(name = "role_id")
        val roleId: Int?
)
