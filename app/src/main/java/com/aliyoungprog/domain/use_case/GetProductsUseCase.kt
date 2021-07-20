package com.aliyoungprog.domain.use_case

import com.aliyoungprog.domain.entity.Product

interface GetProductsUseCase {
    fun getAllBooks(): List<Product>
}
