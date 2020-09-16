package com.project.store_shop.controller

import com.project.store_shop.model.vo.StoreVo
import com.project.store_shop.service.StoreService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RestController
@RequestMapping(value = ["/api/store-service"])
class StoreController(private val storeService: StoreService) {

    @GetMapping(value = [""])
    fun getStoreServices(pageable: Pageable): ResponseEntity<List<StoreVo>> {
        return storeService.getStoreServices(pageable)
    }

    @GetMapping(value = ["/find"])
    fun getStoreService(@RequestParam(value = "id") id: Int): ResponseEntity<StoreVo> {
        return storeService.getStoreService(id)
    }

    @PostMapping(value = ["/update"])
    fun updateStoreService(@RequestBody shopVo: StoreVo): ResponseEntity<Any> {
        return storeService.updateStoreService(shopVo)
    }

    @DeleteMapping(value = ["/delete"])
    fun deleteStoreService(@RequestParam(value = "id") id: Int): ResponseEntity<Any> {
        return storeService.deleteStoreService(id)
    }
}