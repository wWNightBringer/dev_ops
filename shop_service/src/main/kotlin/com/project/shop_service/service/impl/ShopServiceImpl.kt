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
}