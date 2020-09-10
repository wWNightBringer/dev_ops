package com.project.shop_service.query.impl

import com.project.shop_service.model.entity.Shop
import com.project.shop_service.model.vo.ShopVo
import com.project.shop_service.query.ShopQuery
import com.project.shop_service.repository.ShopRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.ArrayList

@Component
class ShopQueryImpl(var shopRepository: ShopRepository) : ShopQuery {
    override fun getShopServices(pageable: Pageable): List<ShopVo> {
        val findAll = shopRepository.findAll(pageable)
        return mapShopToShopVo(findAll)
    }

    private fun mapShopToShopVo(shops: Page<Shop>): List<ShopVo> {
        return shops.map { m ->
            ShopVo(m.Id, m.description, m.volume, m.price)
        }.content
    }
}