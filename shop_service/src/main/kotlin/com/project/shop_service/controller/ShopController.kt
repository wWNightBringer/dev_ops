package com.project.shop_service.controller

import com.project.shop_service.model.vo.ShopVo
import com.project.shop_service.service.ShopService
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RequestMapping(value = ["/api/shop-service"])
@RestController
class ShopController(private val shopService: ShopService) {
    private val logger: Logger = LoggerFactory.getLogger(ShopController::class.java)

    @GetMapping(value = [""])
    fun getShopServices(pageable: Pageable): ResponseEntity<List<ShopVo>> {
        return shopService.getShopServices(pageable)
    }

    @GetMapping(value = ["/find"])
    fun getShopService(@RequestParam(value = "id") id: Int): ResponseEntity<ShopVo> {
        return shopService.getShopService(id)
    }

    @PostMapping(value = ["/update"])
    fun updateShopService(@RequestBody shopVo: ShopVo): ResponseEntity<Any> {
        logger.info("ShopVo description: {}, volume: {}, price {}", shopVo.description, shopVo.volume, shopVo.price)
        return shopService.updateShopService(shopVo)
    }

    @DeleteMapping(value = ["/delete"])
    fun deleteShopService(@RequestParam(value = "id") id: Int): ResponseEntity<Any> {
        return shopService.deleteShopService(id)
    }
}