package com.project.store_shop.query.impl

import com.project.store_shop.model.entity.Store
import com.project.store_shop.model.vo.StoreVo
import com.project.store_shop.query.StoreQuery
import com.project.store_shop.repository.StoreRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.streams.toList

@RequiredArgsConstructor
@Component
class StoreQueryImpl(var storeRepository: StoreRepository) : StoreQuery {

    @Transactional(readOnly = true)
    override fun getStoreServices(pageable: Pageable): List<StoreVo>? {
        val findAll = storeRepository.findAll(pageable)
        return mapStoreToStoreVo(findAll)
    }

    @Transactional(readOnly = true)
    override fun getStoreService(id: Int): StoreVo {
        val findOne = storeRepository.findById(id) ?: return StoreVo()
        return mapStoreToStoreVo(findOne.get())
    }

    @Transactional
    override fun updateStoreService(storeVo: StoreVo) {
        storeRepository.save(mapStoreVoToStore(storeVo))
    }

    @Transactional
    override fun deleteStoreService(id: Int): Boolean {
        getStoreService(id) ?: return false
        storeRepository.deleteById(id)
        return true
    }

    private fun mapStoreToStoreVo(shops: Page<Store>): List<StoreVo>? {
        return shops.content.stream().map { m ->
            createStoreVo(m)
        }.toList()
    }

    private fun mapStoreToStoreVo(shop: Store): StoreVo {
        return Stream.of(shop).map { m -> createStoreVo(m) }.findFirst().get()
    }

    private fun mapStoreVoToStore(storeVo: StoreVo): Store {
        return Stream.of(storeVo).map { m -> Store(m.id, m.description, m.quantityStaff, m.shopId) }.findFirst().get()
    }

    private fun createStoreVo(m: Store): StoreVo {
        val storeVo = StoreVo()
        storeVo.description = m.description
        storeVo.id = m.id
        storeVo.quantityStaff = m.quantityStaff
        storeVo.shopId = m.shopId
        return storeVo
    }
}