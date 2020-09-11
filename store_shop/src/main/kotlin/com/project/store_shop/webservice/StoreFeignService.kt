package com.project.store_shop.webservice

import com.project.store_shop.model.vo.ShopVo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "storeFeign", url = "http://localhost:8081")
interface StoreFeignService {
    @GetMapping(value = ["/api/shop-service"])
    public fun getShopServices(): List<ShopVo>
}