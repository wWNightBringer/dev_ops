package com.project.shop_service.controller

import com.project.shop_service.model.vo.ShopVo
import com.project.shop_service.service.ShopService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RequestMapping(value = ["/api/shop-service"])
@RestController
class ShopController(private val shopService: ShopService) {

    @GetMapping(value = [""])
    fun getShopServices(pageable: Pageable): ResponseEntity<List<ShopVo>> {
        return shopService.getShopServices(pageable)
    }

    @GetMapping(value = ["/find"])
    fun getShopService(@RequestParam(value = "id") id: Int): ResponseEntity<ShopVo> {
        return shopService.getShopService(id)
    }

    @PostMapping(value = ["/update"])
    fun updateShopService(shopVo: ShopVo): ResponseEntity<Any> {
        return shopService.updateShopService(shopVo)
    }

    @DeleteMapping(value = ["/delete"])
    fun deleteShopService(@RequestParam(value = "id") id: Int): ResponseEntity<Any> {
        return shopService.deleteShopService(id)
    }
}