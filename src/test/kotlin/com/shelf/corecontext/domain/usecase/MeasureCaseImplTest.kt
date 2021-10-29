package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.buildMeasure
import com.shelf.corecontext.domain.entity.Measure
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.output.MeasurePersistence
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
class MeasureCaseImplTest {

    @MockK
    private lateinit var  measurePersistence: MeasurePersistence
    @InjectMockKs
    private lateinit var measureUseCase: MeasureUseCaseImpl

    @Test
    fun `should return all categories`() {
        val fakeCategories = listOf<Measure>(buildMeasure(), buildMeasure())
        every { measurePersistence.findAll(0, 10, "asc") } returns fakeCategories
        val categories = measureUseCase.findAll(0, 10, "asc")
        assertEquals(fakeCategories, categories)
        verify (exactly = 1) { measurePersistence.findAll(0, 10, "asc") }
    }

    @Test
    fun `should return a MetricUnit by id`() {
        val id = Random.nextInt()
        val fakeMeasure = buildMeasure(id = id)
        every { measurePersistence.findById(id) } returns Optional.of(fakeMeasure)
        val measure = measureUseCase.findById(id)
        assertEquals(measure.get(), fakeMeasure)
        verify (exactly = 1) { measurePersistence.findById(id) }
    }

    @Test
    fun `should create a MetricUnit`() {
        val fakeMeasure = buildMeasure()
        every { measurePersistence.findByName(fakeMeasure.name)} returns Optional.empty()
        every { measurePersistence.create(fakeMeasure) } returns fakeMeasure
        val newMetricUnit = measureUseCase.create(fakeMeasure)
        assertEquals(fakeMeasure, newMetricUnit)
        verify(exactly = 1) { measurePersistence.findByName(fakeMeasure.name) }
        verify(exactly = 1) {  measurePersistence.create(fakeMeasure) }
    }

    @Test
    fun `should throw error when MetricUnit exists in database`(){
        val fakeMeasure = buildMeasure()
        every { measurePersistence.findByName(fakeMeasure.name)} returns Optional.of(fakeMeasure)
        val error = assertThrows<ResourceExistsException> {
            measureUseCase.create(fakeMeasure)
        }
        assertEquals("Measure already registered", error.message)
    }

    @Test
    fun `should update a exists MetricUnit`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMeasure = buildMeasure(id = id)
        every { measurePersistence.findById(id) } returns Optional.of(fakeMeasure)
        every {measurePersistence.update(fakeMeasure)} returns fakeMeasure

        val MetricUnitSaved = measureUseCase.update(fakeMeasure)

        assertEquals(fakeMeasure, MetricUnitSaved)
        verify(exactly = 1) { measurePersistence.findById(id)  }
        verify(exactly = 1) { measurePersistence.update(fakeMeasure) }
    }

    @Test
    fun `should throw error not found when MetricUnit update`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMeasure = buildMeasure(id = id)
        every { measurePersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            measureUseCase.update(fakeMeasure)
        }
        assertEquals("Measure not found", error.message)
    }

    @Test
    fun `should delete a exists MetricUnit`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMeasure = buildMeasure(id = id)
        every { measurePersistence.findById(id) } returns Optional.of(fakeMeasure)
        every { measurePersistence.delete(fakeMeasure)} just Runs

        measureUseCase.delete(fakeMeasure)

        verify(exactly = 1) { measurePersistence.findById(id)  }
        verify(exactly = 1) { measurePersistence.delete(fakeMeasure) }
    }

    @Test
    fun `should throw error not found when MetricUnit delete`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMeasure = buildMeasure(id = id)
        every { measurePersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            measureUseCase.delete(fakeMeasure)
        }
        assertEquals("Measure not found", error.message)
    }

    @Test
    fun `should delete a exists MetricUnit by id`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMeasure = buildMeasure(id = id)
        every { measurePersistence.findById(id) } returns Optional.of(fakeMeasure)
        every { measurePersistence.deleteById(id)} just Runs

        measureUseCase.delete(id)

        verify(exactly = 1) { measurePersistence.findById(id)  }
        verify(exactly = 1) { measurePersistence.deleteById(id) }
    }

    @Test
    fun `should throw error not found when MetricUnit delete by id`() {
        val id = Random.nextInt(0, 100000 )
        every { measurePersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            measureUseCase.delete(id)
        }
        assertEquals("Measure not found", error.message)
    }

}