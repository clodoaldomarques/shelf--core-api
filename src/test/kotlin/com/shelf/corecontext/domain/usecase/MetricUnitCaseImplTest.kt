package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.buildMetricUnit
import com.shelf.corecontext.domain.entity.MetricUnit
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.output.MetricUnitPersistence
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
class MetricUnitCaseImplTest {

    @MockK
    private lateinit var  metricUnitPersistence: MetricUnitPersistence
    @InjectMockKs
    private lateinit var metricUnitUseCase: MetricUnitUseCaseImpl

    @Test
    fun `should return all categories`() {
        val fakeCategories = listOf<MetricUnit>(buildMetricUnit(), buildMetricUnit())
        every { metricUnitPersistence.findAll(0, 10, "asc") } returns fakeCategories
        val categories = metricUnitUseCase.findAll(0, 10, "asc")
        assertEquals(fakeCategories, categories)
        verify (exactly = 1) { metricUnitPersistence.findAll(0, 10, "asc") }
    }

    @Test
    fun `should return a MetricUnit by id`() {
        val id = Random.nextInt()
        val fakeMetricUnit = buildMetricUnit(id = id)
        every { metricUnitPersistence.findById(id) } returns Optional.of(fakeMetricUnit)
        val MetricUnit = metricUnitUseCase.findById(id)
        assertEquals(MetricUnit.get(), fakeMetricUnit)
        verify (exactly = 1) { metricUnitPersistence.findById(id) }
    }

    @Test
    fun `should create a MetricUnit`() {
        val fakeMetricUnit = buildMetricUnit()
        every { metricUnitPersistence.findByName(fakeMetricUnit.name)} returns Optional.empty()
        every { metricUnitPersistence.create(fakeMetricUnit) } returns fakeMetricUnit
        val newMetricUnit = metricUnitUseCase.create(fakeMetricUnit)
        assertEquals(fakeMetricUnit, newMetricUnit)
        verify(exactly = 1) { metricUnitPersistence.findByName(fakeMetricUnit.name) }
        verify(exactly = 1) {  metricUnitPersistence.create(fakeMetricUnit) }
    }

    @Test
    fun `should throw error when MetricUnit exists in database`(){
        val fakeMetricUnit = buildMetricUnit()
        every { metricUnitPersistence.findByName(fakeMetricUnit.name)} returns Optional.of(fakeMetricUnit)
        val error = assertThrows<ResourceExistsException> {
            metricUnitUseCase.create(fakeMetricUnit)
        }
        assertEquals("MetricUnit already registered", error.message)
    }

    @Test
    fun `should update a exists MetricUnit`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMetricUnit = buildMetricUnit(id = id)
        every { metricUnitPersistence.findById(id) } returns Optional.of(fakeMetricUnit)
        every {metricUnitPersistence.update(fakeMetricUnit)} returns fakeMetricUnit

        val MetricUnitSaved = metricUnitUseCase.update(fakeMetricUnit)

        assertEquals(fakeMetricUnit, MetricUnitSaved)
        verify(exactly = 1) { metricUnitPersistence.findById(id)  }
        verify(exactly = 1) { metricUnitPersistence.update(fakeMetricUnit) }
    }

    @Test
    fun `should throw error not found when MetricUnit update`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMetricUnit = buildMetricUnit(id = id)
        every { metricUnitPersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            metricUnitUseCase.update(fakeMetricUnit)
        }
        assertEquals("MetricUnit not found", error.message)
    }

    @Test
    fun `should delete a exists MetricUnit`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMetricUnit = buildMetricUnit(id = id)
        every { metricUnitPersistence.findById(id) } returns Optional.of(fakeMetricUnit)
        every { metricUnitPersistence.delete(fakeMetricUnit)} just Runs

        metricUnitUseCase.delete(fakeMetricUnit)

        verify(exactly = 1) { metricUnitPersistence.findById(id)  }
        verify(exactly = 1) { metricUnitPersistence.delete(fakeMetricUnit) }
    }

    @Test
    fun `should throw error not found when MetricUnit delete`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMetricUnit = buildMetricUnit(id = id)
        every { metricUnitPersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            metricUnitUseCase.delete(fakeMetricUnit)
        }
        assertEquals("MetricUnit not found", error.message)
    }

    @Test
    fun `should delete a exists MetricUnit by id`() {
        val id = Random.nextInt(0, 100000 )
        val fakeMetricUnit = buildMetricUnit(id = id)
        every { metricUnitPersistence.findById(id) } returns Optional.of(fakeMetricUnit)
        every { metricUnitPersistence.deleteById(id)} just Runs


        metricUnitUseCase.delete(id)

        verify(exactly = 1) { metricUnitPersistence.findById(id)  }
        verify(exactly = 1) { metricUnitPersistence.deleteById(id) }
    }

    @Test
    fun `should throw error not found when MetricUnit delete by id`() {
        val id = Random.nextInt(0, 100000 )
        every { metricUnitPersistence.findById(id)} returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException> {
            metricUnitUseCase.delete(id)
        }
        assertEquals("MetricUnit not found", error.message)
    }

}