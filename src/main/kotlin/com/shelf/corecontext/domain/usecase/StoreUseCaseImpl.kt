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

    override fun create(entity: Store) : Store {
        val entitySaved = storePersistence.findByName(entity.name)
        if (entitySaved.isPresent) throw ResourceExistsException("Store already registered")
        return storePersistence.create(entity)
    }

    override fun update(entity: Store) : Store{
        val entitySaved = storePersistence.findById(entity.id!!)
        if (entitySaved.isEmpty) throw ResourceNotFoundException("Store not found")
        return storePersistence.update(entity)
    }

    override fun delete(entity: Store){
        val entitySaved = storePersistence.findById(entity.id!!)
        if (entitySaved.isEmpty) throw ResourceNotFoundException("Store not found")
        storePersistence.delete(entity)
    }

    override fun delete(id: Int){
        val entitySaved = storePersistence.findById(id)
        if (entitySaved.isEmpty) throw ResourceNotFoundException("Store not found")
        storePersistence.deleteById(id)
    }

}