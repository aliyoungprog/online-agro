package com.aliyoungprog.domain.use_case

import com.aliyoungprog.data.repository.ProductRepositoryImpl
import com.aliyoungprog.domain.entity.Product

class GetProductsUseCaseImpl(private val booksRepository: ProductRepositoryImpl): GetProductsUseCase {
    override fun getAllBooks(): List<Product> = listOf()
}
