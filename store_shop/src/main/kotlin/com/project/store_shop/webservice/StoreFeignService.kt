package com.project.store_shop.webservice

import com.project.store_shop.model.vo.ShopVo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "storeFeign", url = "\${custom.feign.host.shop}")
interface StoreFeignService {
    @GetMapping(value = ["/shop-service"])
    fun getShopServices(): List<ShopVo>

    @GetMapping(value = ["/shop-service/find"])
    fun getShopService(@RequestParam(value = "id") id: Int?): ShopVo
}