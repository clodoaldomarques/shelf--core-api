package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.buildCategory
import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.output.CategoryPersistence
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
class CategoryUseCaseImplTest {

    @MockK
    private lateinit var  categoryPersistence: CategoryPersistence
    @InjectMockKs
    private lateinit var categoryUseCase: CategoryUseCaseImpl

    @Test
    fun `should return all categories`() {
        val fakeCategories = listOf<Category>(buildCategory(), buildCategory())
        every { categoryPersistence.findAll(0, 10, "asc") } returns fakeCategories
        val categories = categoryUseCase.findAll(0, 10, "asc")
        assertEquals(fakeCategories, categories)
        verify (exactly = 1) { categoryPersistence.findAll(0, 10, "asc") }
    }

    @Test
    fun `should return a category by id`() {
        val id = Random.nextInt()
        val fakeCategory = buildCategory(id = id)
        every { categoryPersistence.findById(id) } returns Optional.of(fakeCategory)
        val category = categoryUseCase.findById(id)
        assertEquals(category.get(), fakeCategory)
        verify (exactly = 1) { categoryPersistence.findById(id) }
    }

    @Test
    fun `should create a category`() {
        val fakeCategory = buildCategory()
        every { categoryPersistence.findByNameExactly(fakeCategory.name)} returns Optional.empty()
        every { categoryPersistence.create(fakeCategory) } returns fakeCategory
        val newCategory = categoryUseCase.create(fakeCategory)
        assertEquals(fakeCategory, newCategory)
        verify(exactly = 1) { categoryPersistence.findByNameExactly(fakeCategory.name) }
        verify(exactly = 1) {  categoryPersistence.create(fakeCategory) }
    }

    @Test
    fun `should throw error when category exists in database`(){
        val fakeCategory = buildCategory()
        every { categoryPersistence.findByNameExactly(fakeCategory.name)} returns Optional.of(fakeCategory)
        val error = assertThrows<ResourceExistsException> {
            categoryUseCase.create(fakeCategory)
        }
        assertEquals("Category already registered", error.message)
    }

    @Test
    fun `should update a exists category`() {
        val id = Random.nextInt(0, 100000 )
        val fakeCategory = buildCategory(id = id)
        every { categoryPersistence.findById(id) } returns Optional.of(fakeCategory)
        every {categoryPersistence.update(fakeCategory)} returns fakeCategory

        val categorySaved = categoryUseCase.update(fakeCategory)

        assertEquals(fakeCategory, categorySaved)
        verify(exactly = 1) { categoryPersistence.findById(id)  }
        verify(exactly = 1) { categoryPersistence.update(fakeCategory) }
    }

    @Test
    fun `should throw error not found when category update`() {
        val id = Random.nextInt(0, 100000 )
        val fakeCategory = buildCategory(id = id)
        every { categoryPersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            categoryUseCase.update(fakeCategory)
        }
        assertEquals("Category not found", error.message)
    }

    @Test
    fun `should delete a exists category`() {
        val id = Random.nextInt(0, 100000 )
        val fakeCategory = buildCategory(id = id)
        every { categoryPersistence.findById(id) } returns Optional.of(fakeCategory)
        every { categoryPersistence.delete(fakeCategory)} just Runs

        categoryUseCase.delete(fakeCategory)

        verify(exactly = 1) { categoryPersistence.findById(id)  }
        verify(exactly = 1) { categoryPersistence.delete(fakeCategory) }
    }

    @Test
    fun `should throw error not found when category delete`() {
        val id = Random.nextInt(0, 100000 )
        val fakeCategory = buildCategory(id = id)
        every { categoryPersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            categoryUseCase.delete(fakeCategory)
        }
        assertEquals("Category not found", error.message)
    }

    @Test
    fun `should delete a exists category by id`() {
        val id = Random.nextInt(0, 100000 )
        val fakeCategory = buildCategory(id = id)
        every { categoryPersistence.findById(id) } returns Optional.of(fakeCategory)
        every { categoryPersistence.deleteById(id)} just Runs


        categoryUseCase.delete(id)

        verify(exactly = 1) { categoryPersistence.findById(id)  }
        verify(exactly = 1) { categoryPersistence.deleteById(id) }
    }

    @Test
    fun `should throw error not found when category delete by id`() {
        val id = Random.nextInt(0, 100000 )
        every { categoryPersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            categoryUseCase.delete(id)
        }
        assertEquals("Category not found", error.message)
    }

}