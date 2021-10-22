package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.port.out.CreateCategoryPort
import com.shelf.corecontext.domain.port.out.DeleteCategoryPort
import com.shelf.corecontext.domain.port.out.QueryCategoryPort
import com.shelf.corecontext.domain.port.out.UpdateCategoryPort
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class CategoryUseCase(
    val createCategoryPort: CreateCategoryPort,
    val deleteCategoryPort: DeleteCategoryPort,
    val queryCategoryPort: QueryCategoryPort,
    val updateCategoryPort: UpdateCategoryPort
) {

}