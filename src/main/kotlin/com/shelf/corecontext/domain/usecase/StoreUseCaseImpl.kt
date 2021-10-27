package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.entity.Store
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.input.StoreUseCase
import com.shelf.corecontext.domain.port.output.StorePersistence
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class StoreUseCaseImpl(
    private val storePersistence: StorePersistence,
) : StoreUseCase {
    override fun findAll(page: Int, limit: Int, direction: String?): List<Store> {
        return storePersistence.findAll(page,  limit, direction)
    }

    override fun findById(id : Int): Optional<Store> {
        return storePersistence.findById(id)
    }

    override fun create(Store: Store) : Store {
        val StoreSaved = storePersistence.findByName(Store.name)
        if (StoreSaved.isPresent) throw ResourceExistsException("Store already registered")
        return storePersistence.create(Store)
    }

    override fun update(Store: Store) : Store{
        val StoreSaved = storePersistence.findById(Store.id!!)
        if (StoreSaved.isEmpty) throw ResourceNotFoundException("Store not found")
        return storePersistence.update(Store)
    }

    override fun delete(Store: Store){
        val StoreSaved = storePersistence.findById(Store.id!!)
        if (StoreSaved.isEmpty) throw ResourceNotFoundException("Store not found")
        storePersistence.delete(Store)
    }

    override fun delete(id: Int){
        val StoreSaved = storePersistence.findById(id)
        if (StoreSaved.isEmpty) throw ResourceNotFoundException("Store not found")
        storePersistence.deleteById(id)
    }

}