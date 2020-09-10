package com.project.shop_service.query

import com.project.shop_service.model.vo.ShopVo
import org.springframework.data.domain.Pageable
import java.util.ArrayList

interface ShopQuery {
    fun getShopServices(pageable: Pageable): List<ShopVo>

}
