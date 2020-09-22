package com.project.store_shop.model.entity

import javax.persistence.*

@Entity
@Table(name = "store")
class Store(
        @Id
        @Column(name = "store_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        var description: String?,
        var quantityStaff: Int?,
        @Column(name = "shop_id")
        var shopId:Int?
)
