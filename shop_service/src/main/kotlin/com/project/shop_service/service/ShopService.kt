package com.project.shop_service.service

import com.project.shop_service.model.vo.ShopVo
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity

interface ShopService {
    fun getShopServices(pageable: Pageable): ResponseEntity<List<ShopVo>>
    fun getShopService(id: Int): ResponseEntity<ShopVo>
    fun updateShopService(shopVo: ShopVo): ResponseEntity<Any>
    fun deleteShopService(id: Int): ResponseEntity<Any>

}
