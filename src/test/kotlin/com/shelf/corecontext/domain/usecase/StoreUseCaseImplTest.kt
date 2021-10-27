package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.buildStore
import com.shelf.corecontext.domain.entity.Store
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.output.StorePersistence
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
class StoreUseCaseImplTest {

    @MockK
    private lateinit var  storePersistence: StorePersistence
    @InjectMockKs
    private lateinit var storeUseCase: StoreUseCaseImpl

    @Test
    fun `should return all categories`() {
        val fakeCategories = listOf<Store>(buildStore(), buildStore())
        every { storePersistence.findAll(0, 10, "asc") } returns fakeCategories
        val categories = storeUseCase.findAll(0, 10, "asc")
        assertEquals(fakeCategories, categories)
        verify (exactly = 1) { storePersistence.findAll(0, 10, "asc") }
    }

    @Test
    fun `should return a Store by id`() {
        val id = Random.nextInt()
        val fakeStore = buildStore(id = id)
        every { storePersistence.findById(id) } returns Optional.of(fakeStore)
        val Store = storeUseCase.findById(id)
        assertEquals(Store.get(), fakeStore)
        verify (exactly = 1) { storePersistence.findById(id) }
    }

    @Test
    fun `should create a Store`() {
        val fakeStore = buildStore()
        every { storePersistence.findByName(fakeStore.name)} returns Optional.empty()
        every { storePersistence.create(fakeStore) } returns fakeStore
        val newStore = storeUseCase.create(fakeStore)
        assertEquals(fakeStore, newStore)
        verify(exactly = 1) { storePersistence.findByName(fakeStore.name) }
        verify(exactly = 1) {  storePersistence.create(fakeStore) }
    }

    @Test
    fun `should throw error when Store exists in database`(){
        val fakeStore = buildStore()
        every { storePersistence.findByName(fakeStore.name)} returns Optional.of(fakeStore)
        val error = assertThrows<ResourceExistsException> {
            storeUseCase.create(fakeStore)
        }
        assertEquals("Store already registered", error.message)
    }

    @Test
    fun `should update a exists Store`() {
        val id = Random.nextInt(0, 100000 )
        val fakeStore = buildStore(id = id)
        every { storePersistence.findById(id) } returns Optional.of(fakeStore)
        every {storePersistence.update(fakeStore)} returns fakeStore

        val StoreSaved = storeUseCase.update(fakeStore)

        assertEquals(fakeStore, StoreSaved)
        verify(exactly = 1) { storePersistence.findById(id)  }
        verify(exactly = 1) { storePersistence.update(fakeStore) }
    }

    @Test
    fun `should throw error not found when Store update`() {
        val id = Random.nextInt(0, 100000 )
        val fakeStore = buildStore(id = id)
        every { storePersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            storeUseCase.update(fakeStore)
        }
        assertEquals("Store not found", error.message)
    }

    @Test
    fun `should delete a exists Store`() {
        val id = Random.nextInt(0, 100000 )
        val fakeStore = buildStore(id = id)
        every { storePersistence.findById(id) } returns Optional.of(fakeStore)
        every { storePersistence.delete(fakeStore)} just Runs

        storeUseCase.delete(fakeStore)

        verify(exactly = 1) { storePersistence.findById(id)  }
        verify(exactly = 1) { storePersistence.delete(fakeStore) }
    }

    @Test
    fun `should throw error not found when Store delete`() {
        val id = Random.nextInt(0, 100000 )
        val fakeStore = buildStore(id = id)
        every { storePersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            storeUseCase.delete(fakeStore)
        }
        assertEquals("Store not found", error.message)
    }

    @Test
    fun `should delete a exists Store by id`() {
        val id = Random.nextInt(0, 100000 )
        val fakeStore = buildStore(id = id)
        every { storePersistence.findById(id) } returns Optional.of(fakeStore)
        every { storePersistence.deleteById(id)} just Runs


        storeUseCase.delete(id)

        verify(exactly = 1) { storePersistence.findById(id)  }
        verify(exactly = 1) { storePersistence.deleteById(id) }
    }

    @Test
    fun `should throw error not found when Store delete by id`() {
        val id = Random.nextInt(0, 100000 )
        every { storePersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            storeUseCase.delete(id)
        }
        assertEquals("Store not found", error.message)
    }

}