package com.project.shop_service.repository

import com.project.shop_service.model.entity.Shop
import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository :JpaRepository<Shop,Int>{

}
