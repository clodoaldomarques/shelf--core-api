package com.shelf.corecontext.domain.port.output

import com.shelf.corecontext.domain.entity.Inventory
import com.shelf.corecontext.domain.entity.Store
import java.util.*

interface InventoryPersistence {
    fun create(entity: Inventory) : Inventory
    fun update(entitySaved: Inventory) : Inventory
    fun delete(entity: Inventory)
    fun findByStoreIdAndBarCode(storeId : Int, barCode: String): Optional<Inventory>
    fun findAllByStore(store : Store, page : Int, limit : Int, direction : String?): List<Inventory>
    fun findByStoreAndFilters(store: Store, productDescription : String?, page : Int, limit : Int, direction : String?): List<Inventory>
    fun findById(id: Int): Optional<Inventory>
    fun findInventoryByProductBarCode(barCode: String): List<Inventory>
}