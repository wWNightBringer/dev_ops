package com.project.shop_service.query.impl

import com.project.shop_service.model.entity.Shop
import com.project.shop_service.model.vo.ShopVo
import com.project.shop_service.query.ShopQuery
import com.project.shop_service.repository.ShopRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Stream

@Component
class ShopQueryImpl(var shopRepository: ShopRepository) : ShopQuery {
    @Transactional(readOnly = true)
    override fun getShopServices(pageable: Pageable): List<ShopVo> {
        val findAll = shopRepository.findAll(pageable)
        return mapShopToShopVo(findAll)
    }

    @Transactional(readOnly = true)
    override fun getShopService(id: Int): ShopVo {
        val findOne = shopRepository.findById(id)
        if (findOne.isEmpty) return ShopVo()
        return mapShopToShopVo(findOne.get())
    }

    @Transactional
    override fun updateShopService(shopVo: ShopVo) {
        shopRepository.save(mapShopVoToShop(shopVo))
    }

    @Transactional
    override fun deleteShopService(id: Int): Boolean {
        val shopService = getShopService(id) ?: return false
        shopRepository.deleteById(id)
        return true
    }

    private fun mapShopToShopVo(shops: Page<Shop>): List<ShopVo> {
        return shops.map { m ->
            ShopVo(m.id, m.description, m.volume, m.price)
        }.content
    }

    private fun mapShopToShopVo(shop: Shop): ShopVo {
        return Stream.of(shop).map { m -> ShopVo(m.id, m.description, m.volume, m.price) }.findFirst().get()
    }

    private fun mapShopVoToShop(shopVo: ShopVo): Shop {
        return Stream.of(shopVo).map { m -> Shop(m.id, m.description, m.volume, m.price) }.findFirst().get()
    }
}