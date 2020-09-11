package com.project.store_shop.query

import com.project.store_shop.model.vo.StoreVo
import org.springframework.data.domain.Pageable

interface StoreQuery {
    fun getStoreServices(pageable: Pageable): List<StoreVo>?
    fun getStoreService(id: Int): StoreVo
    fun updateStoreService(storeVo: StoreVo)
    fun deleteStoreService(id: Int): Boolean

}
