package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.entity.Inventory
import com.shelf.corecontext.domain.entity.Product
import com.shelf.corecontext.domain.entity.Store
import com.shelf.corecontext.domain.exceptions.GenericBusinessException
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.input.InventoryUseCase
import com.shelf.corecontext.domain.port.output.InventoryPersistence
import java.math.BigDecimal
import java.util.*
import javax.transaction.Transactional

class InventoryUseCaseImpl (
    var inventoryPersistence: InventoryPersistence
) : InventoryUseCase {

    @Transactional
    override fun create(entity: Inventory): Inventory {
        val optional = inventoryPersistence.findByStoreIdAndBarCode(entity.store.id!!, entity.product.barCode)
        if (optional.isPresent) throw ResourceExistsException("Inventory already registered")
        return inventoryPersistence.create(entity)
    }

    @Transactional
    override fun delete(entity: Inventory) {
        val entitySaved = inventoryPersistence.findById(entity.id!!)
        if (entitySaved.isEmpty) throw ResourceNotFoundException("Inventory not found")
        inventoryPersistence.delete(entitySaved.get())
    }

    @Transactional
    override fun increase(store: Store, product: Product, value: BigDecimal) {
        val optional = inventoryPersistence.findByStoreIdAndBarCode(store.id!!, product.barCode)
        if (optional.isEmpty) throw ResourceNotFoundException("Inventory not found")
        val entitySaved = optional.get()
        entitySaved.increase(value)
        inventoryPersistence.update(entitySaved)
    }

    @Transactional
    override fun decrease(store: Store, product: Product, value: BigDecimal) {
        val optional = inventoryPersistence.findByStoreIdAndBarCode(store.id!!, product.barCode)
        if (optional.isEmpty) throw ResourceNotFoundException("Inventory not found")
        val entitySaved = optional.get()
        entitySaved.decrease(value)
        inventoryPersistence.update(entitySaved)
    }

    @Transactional
    override fun updatePurchasePrice(product: String, value: BigDecimal) {
        val inventories = inventoryPersistence.findInventoryByProductBarCode(product)
        inventories.map { i -> i.updatePurchasePrice(value) }
        inventories.forEach(){
            inventoryPersistence.update(it)
        }
    }

    @Transactional
    override fun updateSalePrice(product: String, value: BigDecimal) {
        val inventories = inventoryPersistence.findInventoryByProductBarCode(product)
        inventories.map { i -> i.updateSalePrice(value) }
        inventories.forEach(){
            inventoryPersistence.update(it)
        }
    }

    @Transactional
    override fun transfer(product: Product, origin: Store, destiny: Store, value: BigDecimal) {
        val optionalOrigin = inventoryPersistence.findByStoreIdAndBarCode(origin.id!!, product.barCode)
        val optionalDestiny = inventoryPersistence.findByStoreIdAndBarCode(destiny.id!!, product.barCode)
        if (optionalOrigin.isEmpty || optionalDestiny.isEmpty) throw ResourceNotFoundException("Inventory not found")
        optionalOrigin.get().decrease(value)
        inventoryPersistence.update(optionalOrigin.get())
        optionalDestiny.get().increase(value)
        inventoryPersistence.update(optionalDestiny.get())
    }

    override fun findByStoreAndProduct(store: Store, product: Product): Optional<Inventory> {
        return inventoryPersistence.findByStoreIdAndBarCode(store.id!!, product.barCode)
    }

    override fun findAllByStore(store: Store, page: Int, limit: Int, direction: String?): List<Inventory> {
        return inventoryPersistence.findAllByStore(store, page, limit, direction)
    }

    override fun findByStoreAndFilters(
        store: Store,
        productDescription: String?,
        page: Int,
        limit: Int,
        direction: String?
    ): List<Inventory> {
        return inventoryPersistence.findByStoreAndFilters(store, productDescription, page, limit, direction)
    }

}