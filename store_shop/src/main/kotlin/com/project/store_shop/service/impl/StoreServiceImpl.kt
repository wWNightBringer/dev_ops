package com.project.store_shop.service.impl

import com.project.store_shop.model.vo.StoreVo
import com.project.store_shop.query.StoreQuery
import com.project.store_shop.service.StoreService
import com.project.store_shop.webservice.StoreFeignService
import lombok.RequiredArgsConstructor
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@RequiredArgsConstructor
@Component
class StoreServiceImpl(private val storeQuery: StoreQuery,
                       private val storeFeignService: StoreFeignService) : StoreService {

    override fun getStoreServices(pageable: Pageable): ResponseEntity<List<StoreVo>> {
        val stores: List<StoreVo>? = storeQuery.getStoreServices(pageable)
        val shopServices = storeFeignService.getShopServices()
        stores!!.forEach { p ->
            shopServices.stream().filter { v -> p.shopId == v.id }.forEach { v ->
                p.shopDescription = v.description
            }
        }
        return ResponseEntity.ok().body(stores)
    }

    override fun getStoreService(id: Int): ResponseEntity<StoreVo> {
        val store: StoreVo = storeQuery.getStoreService(id)
        if (store.shopId != null) {
            val shopService = storeFeignService.getShopService(store.shopId)
            store.shopDescription = shopService.description
        }
        return ResponseEntity.ok().body(store)
    }

    override fun updateStoreService(storeVo: StoreVo): ResponseEntity<Any> {
        storeQuery.updateStoreService(storeVo)
        return ResponseEntity.ok().body(StoreVo())
    }

    override fun deleteStoreService(id: Int): ResponseEntity<Any> {
        val flag: Boolean = storeQuery.deleteStoreService(id)
        if (flag) return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(StoreVo())
    }
}