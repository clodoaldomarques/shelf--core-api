package com.shelf.corecontext.domain.port.input

import com.shelf.corecontext.domain.entity.Inventory
import com.shelf.corecontext.domain.entity.Product
import com.shelf.corecontext.domain.entity.Store
import java.math.BigDecimal
import java.util.*

interface InventoryUseCase {
    fun create(entity: Inventory) : Inventory
    fun delete(entity: Inventory)
    fun increase(store: Store, product: Product, value: BigDecimal)
    fun decrease(store: Store, product: Product, value: BigDecimal)
    fun updatePurchasePrice(product: Product, value: BigDecimal)
    fun updateSalePrice(product: Product, value: BigDecimal)
    fun transfer(product: Product, origin : Store, destiny : Store, value: BigDecimal)
    fun findByStoreAndProduct(store : Store, product: Product): Optional<Inventory>
    fun findAllByStore(store : Store, page : Int, limit : Int, direction : String?): List<Inventory>
    fun findByStoreAndFilters(store: Store, productDescription : String?, page : Int, limit : Int, direction : String?): List<Inventory>
}