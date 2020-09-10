package com.project.shop_service.controller

import com.project.shop_service.model.vo.ShopVo
import com.project.shop_service.service.ShopService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RequestMapping(value = ["/api/shop-service"])
@RestController
class ShopController(private val shopService: ShopService) {

    @GetMapping(value = [""])
    fun getShopServices(pageable: Pageable): ResponseEntity<List<ShopVo>> {
        return shopService.getShopServices(pageable)
    }
}