package com.project.store_shop.service

import com.project.store_shop.model.vo.StoreVo
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity

interface StoreService {
    fun getStoreServices(pageable: Pageable): ResponseEntity<List<StoreVo>>
    fun getStoreService(id: Int): ResponseEntity<StoreVo>
    fun updateStoreService(storeVo: StoreVo): ResponseEntity<Any>
    fun deleteStoreService(id: Int): ResponseEntity<Any>

}
