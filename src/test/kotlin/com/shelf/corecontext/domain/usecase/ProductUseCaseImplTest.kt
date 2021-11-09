package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.buildProduct
import com.shelf.corecontext.domain.entity.Product
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.input.ProductUseCase
import com.shelf.corecontext.domain.port.output.ProductPersistence
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*
import kotlin.random.Random

@ExtendWith(MockKExtension::class)
class ProductUseCaseImplTest {

    @MockK
    private lateinit var  productPersistence: ProductPersistence
    @InjectMockKs
    private lateinit var productUseCase: ProductUseCaseImpl

    @Test
    fun `should return all products`() {
        val fakeProducts = listOf<Product>(buildProduct(), buildProduct())
        every { productPersistence.findAll(0, 10, "asc") } returns fakeProducts
        val categories = productUseCase.findAll(0, 10, "asc")
        assertEquals(fakeProducts, categories)
        verify (exactly = 1) { productPersistence.findAll(0, 10, "asc") }
    }

    @Test
    fun `should return a Product by barCode`() {
        val barCode = UUID.randomUUID().toString()
        val fakeProduct = buildProduct(barCode = barCode)
        every { productPersistence.findByBarCode(barCode) } returns Optional.of(fakeProduct)
        val product = productUseCase.findByBarCode(barCode)
        assertEquals(product.get(), fakeProduct)
        verify (exactly = 1) { productPersistence.findByBarCode(barCode) }
    }

    @Test
    fun `should create a Product`() {
        val fakeProduct = buildProduct()
        every { productPersistence.findByName(fakeProduct.name)} returns Optional.empty()
        every { productPersistence.create(fakeProduct) } returns fakeProduct
        val newProduct = productUseCase.create(fakeProduct)
        assertEquals(fakeProduct, newProduct)
        verify(exactly = 1) { productPersistence.findByName(fakeProduct.name) }
        verify(exactly = 1) {  productPersistence.create(fakeProduct) }
    }

    @Test
    fun `should throw error when Product exists in database`(){
        val fakeProduct = buildProduct()
        every { productPersistence.findByName(fakeProduct.name)} returns Optional.of(fakeProduct)
        val error = assertThrows<ResourceExistsException> {
            productUseCase.create(fakeProduct)
        }
        assertEquals("Product already registered", error.message)
    }

    @Test
    fun `should update a exists Product`() {
        val barCode = UUID.randomUUID().toString()
        val fakeProduct = buildProduct(barCode = barCode)
        every { productPersistence.findByBarCode(barCode) } returns Optional.of(fakeProduct)
        every {productPersistence.update(fakeProduct)} returns fakeProduct

        val productSaved = productUseCase.update(fakeProduct)

        assertEquals(fakeProduct, productSaved)
        verify(exactly = 1) { productPersistence.findByBarCode(barCode)  }
        verify(exactly = 1) { productPersistence.update(fakeProduct) }
    }

    @Test
    fun `should throw error not found when Product update`() {
        val barCode = UUID.randomUUID().toString()
        val fakeProduct = buildProduct(barCode = barCode)
        every { productPersistence.findByBarCode(barCode)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            productUseCase.update(fakeProduct)
        }
        assertEquals("Product not found", error.message)
    }

    @Test
    fun `should delete a exists Product`() {
        val barCode = UUID.randomUUID().toString()
        val fakeProduct = buildProduct(barCode = barCode)
        every { productPersistence.findByBarCode(barCode) } returns Optional.of(fakeProduct)
        every { productPersistence.delete(fakeProduct)} just Runs

        productUseCase.delete(fakeProduct)

        verify(exactly = 1) { productPersistence.findByBarCode(barCode) }
        verify(exactly = 1) { productPersistence.delete(fakeProduct) }
    }

    @Test
    fun `should throw error not found when Product delete`() {
        val barCode = UUID.randomUUID().toString()
        val fakeProduct = buildProduct(barCode = barCode)
        every { productPersistence.findByBarCode(barCode)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            productUseCase.delete(fakeProduct)
        }
        assertEquals("Product not found", error.message)
    }

    @Test
    fun `should delete a exists Product by barcode`() {
        val barCode = UUID.randomUUID().toString()
        val fakeProduct = buildProduct(barCode = barCode)
        every { productPersistence.findByBarCode(barCode) } returns Optional.of(fakeProduct)
        every { productPersistence.deleteByBarCode(barCode)} just Runs


        productUseCase.delete(barCode)

        verify(exactly = 1) { productPersistence.findByBarCode(barCode)  }
        verify(exactly = 1) { productPersistence.deleteByBarCode(barCode) }
    }

    @Test
    fun `should throw error not found when Product delete by barcode`() {
        val barCode = UUID.randomUUID().toString()
        every { productPersistence.findByBarCode(barCode)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            productUseCase.delete(barCode)
        }
        assertEquals("Product not found", error.message)
    }

}