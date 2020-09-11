package com.project.shop_service.service.impl

import com.project.shop_service.model.vo.ShopVo
import com.project.shop_service.query.ShopQuery
import com.project.shop_service.service.ShopService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ShopServiceImpl(private val shopQuery: ShopQuery) : ShopService {

    override fun getShopServices(pageable: Pageable): ResponseEntity<List<ShopVo>> {
        val shops: List<ShopVo> = shopQuery.getShopServices(pageable)
        return ResponseEntity.ok().body(shops)
    }

    override fun getShopService(id: Int): ResponseEntity<ShopVo> {
        val shop: ShopVo = shopQuery.getShopService(id)
        return ResponseEntity.ok().body(shop)
    }

    override fun updateShopService(shopVo: ShopVo): ResponseEntity<Any> {
        shopQuery.updateShopService(shopVo)
        return ResponseEntity.ok().body(ShopVo())
    }

    override fun deleteShopService(id: Int): ResponseEntity<Any> {
        val flag: Boolean = shopQuery.deleteShopService(id)
        if (flag) return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(ShopVo())
    }
}