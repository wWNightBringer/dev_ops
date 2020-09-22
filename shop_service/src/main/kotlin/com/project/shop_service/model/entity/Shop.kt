package com.project.shop_service.model.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "shop")
class Shop(
        @Id
        @Column(name = "shop_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        var description: String?,
        var volume: Double?,
        var price: BigDecimal?)
