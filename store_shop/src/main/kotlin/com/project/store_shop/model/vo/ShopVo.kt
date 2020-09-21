package com.project.store_shop.model.vo

import lombok.Builder
import lombok.Data
import java.io.Serializable
import java.math.BigDecimal

@Data
@Builder
class ShopVo : Serializable {
    var id: Int? = null
    var description: String? = null
    var volume: Double? = null
    var price: BigDecimal? = null

    constructor(id: Int?, description: String?, volume: Double?, price: BigDecimal?) {
        this.id = id
        this.description = description
        this.volume = volume
        this.price = price
    }

    constructor()

}