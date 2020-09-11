package com.project.shop_service.model.vo

import lombok.Builder
import lombok.Data
import java.io.Serializable
import java.math.BigDecimal

@Data
@Builder
class ShopVo(var id: Int?, var description: String?, var volume: Double?, var price: BigDecimal?) : Serializable {
    constructor() : this(null, null, null, null)
}