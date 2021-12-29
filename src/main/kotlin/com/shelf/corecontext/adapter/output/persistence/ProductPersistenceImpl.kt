package com.shelf.corecontext.adapter.output.persistence

import com.shelf.corecontext.domain.entity.Product
import com.shelf.corecontext.domain.port.output.ProductPersistence
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductPersistenceImpl : ProductPersistence {
    override fun create(entity: Product): Product {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Product) {
        TODO("Not yet implemented")
    }

    override fun deleteByBarCode(id: String) {
        TODO("Not yet implemented")
    }

    override fun findAll(page: Int, limit: Int, direction: String?): List<Product> {
        TODO("Not yet implemented")
    }

    override fun findByBarCode(id: String): Optional<Product> {
        TODO("Not yet implemented")
    }

    override fun findByName(name: String): Optional<Product> {
        TODO("Not yet implemented")
    }

    override fun update(entity: Product): Product {
        TODO("Not yet implemented")
    }
}