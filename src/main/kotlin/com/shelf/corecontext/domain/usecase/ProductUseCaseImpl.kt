package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.entity.Product
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.input.ProductUseCase
import com.shelf.corecontext.domain.port.output.ProductPersistence
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ProductUseCaseImpl(
    private val productPersistence: ProductPersistence,
) : ProductUseCase {
    override fun findAll(page: Int, limit: Int, direction: String?): List<Product> {
        return productPersistence.findAll(page,  limit, direction)
    }

    override fun findByBarCode(barCode : String): Optional<Product> {
        return productPersistence.findByBarCode(barCode)
    }

    override fun create(entity: Product) : Product {
        val entitySaved = productPersistence.findByName(entity.name)
        if (entitySaved.isPresent) throw ResourceExistsException("Product already registered")
        return productPersistence.create(entity)
    }

    override fun update(entity: Product) : Product{
        val entitySaved = productPersistence.findByBarCode(entity.barCode)
        if (entitySaved.isEmpty) throw ResourceNotFoundException("Product not found")
        return productPersistence.update(entity)
    }

    override fun delete(entity: Product){
        val entitySaved = productPersistence.findByBarCode(entity.barCode)
        if (entitySaved.isEmpty) throw ResourceNotFoundException("Product not found")
        productPersistence.delete(entity)
    }

    override fun delete(barCode : String){
        val entitySaved = productPersistence.findByBarCode(barCode)
        if (entitySaved.isEmpty) throw ResourceNotFoundException("Product not found")
        productPersistence.deleteByBarCode(barCode)
    }

}