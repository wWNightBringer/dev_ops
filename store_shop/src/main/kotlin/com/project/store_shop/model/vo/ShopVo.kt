package com.project.store_shop.model.vo

import lombok.Builder
import lombok.Data
import java.math.BigDecimal

@Data
@Builder
class ShopVo(var id: Int?, var description: String?, var volume: Double?, var price: BigDecimal?) {
    constructor() : this(null, null, null, null)
}