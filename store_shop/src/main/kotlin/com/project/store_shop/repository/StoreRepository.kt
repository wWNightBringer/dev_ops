package com.project.store_shop.repository

import com.project.store_shop.model.entity.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Int> {

}
