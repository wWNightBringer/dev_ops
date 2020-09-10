package com.project.shop_service.model.vo

import lombok.Builder
import lombok.Data
import java.math.BigDecimal

@Data
@Builder
class ShopVo(var Id: Int, var description: String, var volume: Double, var price: BigDecimal)