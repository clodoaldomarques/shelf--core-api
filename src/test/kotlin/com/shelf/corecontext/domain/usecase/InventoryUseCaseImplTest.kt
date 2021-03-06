package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.buildCategory
import com.shelf.corecontext.buildInventory
import com.shelf.corecontext.buildProduct
import com.shelf.corecontext.buildStore
import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.entity.Inventory
import com.shelf.corecontext.domain.exceptions.GenericBusinessException
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.output.InventoryPersistence
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

@ExtendWith(MockKExtension::class)
class InventoryUseCaseImplTest {

    @MockK
    private lateinit var  inventoryPersistence: InventoryPersistence
    @InjectMockKs
    private lateinit var inventoryUseCase: InventoryUseCaseImpl

    val inventorySlot = slot<Inventory>()

    @Test
    fun `should return all inventories by store`() {
        val id = Random.nextInt(0, 100000 )
        val fakeStore = buildStore(id = id)
        val fakeInventories = listOf<Inventory>(buildInventory(store = fakeStore), buildInventory(store = fakeStore))
        every { inventoryPersistence.findAllByStore(fakeStore, 0, 10, "asc") } returns fakeInventories
        val inventories = inventoryUseCase.findAllByStore(fakeStore, 0, 10, "asc")
        assertEquals(fakeInventories, inventories)
        verify (exactly = 1) { inventoryPersistence.findAllByStore(fakeStore, 0, 10, "asc") }
    }

    @Test
    fun `should return a Inventory by Store and Product`() {
        val id = Random.nextInt(0, 100000 )
        val fakeStore = buildStore(id = id)
        val fakeProduct = buildProduct()
        val fakeInventory = buildInventory(store = fakeStore, product = fakeProduct)
        every { inventoryPersistence.findByStoreIdAndBarCode(fakeStore.id!!, fakeProduct.barCode) } returns Optional.of(fakeInventory)
        val inventory = inventoryUseCase.findByStoreAndProduct(fakeStore, fakeProduct)
        assertEquals(inventory.get(), fakeInventory)
        verify (exactly = 1) { inventoryPersistence.findByStoreIdAndBarCode(fakeStore.id!!, fakeProduct.barCode) }
    }

    @Test
    fun `should create a Inventory`() {
        val id = Random.nextInt(0, 100000 )
        val fakeStore = buildStore(id = id)
        val fakeInventory = buildInventory(store = fakeStore)
        every { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode) } returns Optional.empty()
        every { inventoryPersistence.create(fakeInventory)} returns fakeInventory
        val newInventory = inventoryUseCase.create(fakeInventory)
        assertEquals(fakeInventory, newInventory)
        verify(exactly = 1) { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode) }
        verify(exactly = 1) {  inventoryPersistence.create(fakeInventory) }
    }

    @Test
    fun `should throw error when Inventory exists in database`(){
        val id = Random.nextInt(0, 100000 )
        val fakeInventory = buildInventory(id = id, store = buildStore(id = id))
        every { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode)} returns Optional.of(fakeInventory)
        val error = assertThrows<ResourceExistsException> {
            inventoryUseCase.create(fakeInventory)
        }
        assertEquals("Inventory already registered", error.message)
    }

    @Test
    fun `should delete a exists Inventory`() {
        val id = Random.nextInt(0, 100000 )
        val fakeInventory = buildInventory(id = id)
        every { inventoryPersistence.findById(id) } returns Optional.of(fakeInventory)
        every { inventoryPersistence.delete(fakeInventory)} just Runs

        inventoryUseCase.delete(fakeInventory)

        verify(exactly = 1) { inventoryPersistence.findById(id)  }
        verify(exactly = 1) { inventoryPersistence.delete(fakeInventory) }
    }

    @Test
    fun `should throw error not found when Inventory delete`() {
        val id = Random.nextInt(0, 100000 )
        val fakeInventory = buildInventory(id = id, store = buildStore(id = id))
        every { inventoryPersistence.findById(id) } returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            inventoryUseCase.delete(fakeInventory)
        }
        assertEquals("Inventory not found", error.message)
    }

    @Test
    fun `should increase product balance on inventory`(){
        val id = Random.nextInt(0, 100000 )
        val fakeInventory = buildInventory(id = id, store = buildStore(id = id))
        val increaseValue = BigDecimal.ONE
        every { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode)} returns Optional.of(fakeInventory)
        every { inventoryPersistence.update(fakeInventory)} returns fakeInventory

        inventoryUseCase.increase(fakeInventory.store, fakeInventory.product, increaseValue)

        verify(exactly = 1) { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode) }
        verify(exactly = 1) { inventoryPersistence.update(capture(inventorySlot)) }

        assertEquals(fakeInventory, inventorySlot.captured)
    }

    @Test
    fun `should throw error not found when inventory increase`() {
        val id = Random.nextInt(0, 100000 )
        val fakeInventory = buildInventory(id = id, store = buildStore(id = id))
        val increaseValue = BigDecimal.ONE
        every { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode) } returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            inventoryUseCase.increase(fakeInventory.store, fakeInventory.product, increaseValue)
        }
        assertEquals("Inventory not found", error.message)
    }


    @Test
    fun `should decrease product balance on inventory`(){
        val id = Random.nextInt(0, 100000 )
        val fakeInventory = buildInventory(id = id, store = buildStore(id = id))
        val decreaseValue = BigDecimal.ONE
        every { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode)} returns Optional.of(fakeInventory)
        every { inventoryPersistence.update(fakeInventory)} returns fakeInventory

        inventoryUseCase.decrease(fakeInventory.store, fakeInventory.product, decreaseValue)

        verify(exactly = 1) { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode) }
        verify(exactly = 1) { inventoryPersistence.update(capture(inventorySlot)) }

        assertEquals(fakeInventory, inventorySlot.captured)
    }

    @Test
    fun `should throw error not found when inventory decrease`() {
        val id = Random.nextInt(0, 100000 )
        val fakeInventory = buildInventory(id = id, store = buildStore(id = id))
        val decreaseValue = BigDecimal.ONE
        every { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode) } returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            inventoryUseCase.decrease(fakeInventory.store, fakeInventory.product, decreaseValue)
        }
        assertEquals("Inventory not found", error.message)
    }

    @Test
    fun `should throw error balance in stock cannot be less than zero when inventory decrease`() {
        val id = Random.nextInt(0, 100000 )
        val fakeInventory = buildInventory(id = id, store = buildStore(id = id), balance = BigDecimal.ZERO)
        val decreaseValue = BigDecimal.ONE
        every { inventoryPersistence.findByStoreIdAndBarCode(fakeInventory.store.id!!, fakeInventory.product.barCode)  } returns Optional.of(fakeInventory)
        val error = assertThrows<GenericBusinessException> {
            inventoryUseCase.decrease(fakeInventory.store, fakeInventory.product, decreaseValue)
        }
        assertEquals("Balance in stock cannot be less than zero", error.message)
    }

    @Test
    fun `should update purchase price on inventory`(){
        val fakeBarCode = UUID.randomUUID().toString()
        val fakeInventories = listOf<Inventory>(buildInventory(), buildInventory())
        val fakePurchasePrice = BigDecimal.TEN

        every { inventoryPersistence.findInventoryByProductBarCode(fakeBarCode)} returns fakeInventories

        fakeInventories.forEach(){
            every { inventoryPersistence.update(it)} returns it
        }

        inventoryUseCase.updatePurchasePrice(fakeBarCode, fakePurchasePrice)

        verify(exactly = 1) { inventoryPersistence.findInventoryByProductBarCode(fakeBarCode) }
        fakeInventories.forEach(){
            verify(exactly = 1) { inventoryPersistence.update(it) }
            assertEquals(BigDecimal.TEN, it.purchasePrice)
        }

    }

    @Test
    fun `should throw error negative purchase price in inventory`() {
        val fakeBarCode = UUID.randomUUID().toString()
        val fakeInventories = listOf<Inventory>(buildInventory(), buildInventory())
        val fakePurchasePrice = BigDecimal(-1.00)

        every { inventoryPersistence.findInventoryByProductBarCode(fakeBarCode)} returns fakeInventories
        fakeInventories.forEach(){
            every { inventoryPersistence.update(it)} returns it
        }
        val error = assertThrows<GenericBusinessException> {
            inventoryUseCase.updatePurchasePrice(fakeBarCode, fakePurchasePrice)
        }
        assertEquals("Negative value for purchase price", error.message)
    }



    @Test
    fun `should update sale price on inventory`(){
        val fakeBarCode = UUID.randomUUID().toString()
        val fakeInventories = listOf<Inventory>(buildInventory(), buildInventory())
        val fakePurchasePrice = BigDecimal.TEN

        every { inventoryPersistence.findInventoryByProductBarCode(fakeBarCode)} returns fakeInventories
        fakeInventories.forEach(){
            every { inventoryPersistence.update(it)} returns it
        }

        inventoryUseCase.updateSalePrice(fakeBarCode, fakePurchasePrice)

        verify(exactly = 1) { inventoryPersistence.findInventoryByProductBarCode(fakeBarCode) }
        fakeInventories.forEach(){
            verify(exactly = 1) { inventoryPersistence.update(it) }
            assertEquals(BigDecimal.TEN, it.salePrice)
        }

    }

    @Test
    fun `should throw error negative sale price in inventory`() {
        val fakeBarCode = UUID.randomUUID().toString()
        val fakeInventories = listOf<Inventory>(buildInventory(), buildInventory())
        val fakePurchasePrice = BigDecimal(-1.00)

        every { inventoryPersistence.findInventoryByProductBarCode(fakeBarCode)} returns fakeInventories
        fakeInventories.forEach(){
            every { inventoryPersistence.update(it)} returns it
        }
        val error = assertThrows<GenericBusinessException> {
            inventoryUseCase.updateSalePrice(fakeBarCode, fakePurchasePrice)
        }
        assertEquals("Negative value for sale price", error.message)
    }


}