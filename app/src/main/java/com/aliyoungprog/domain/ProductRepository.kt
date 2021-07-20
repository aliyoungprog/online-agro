package com.aliyoungprog.domain

import android.content.Context
import com.aliyoungprog.domain.entity.Product
import java.util.*

interface ProductRepository {
    suspend fun getAllProducts(getAllBooks:(List<Product>)->Unit)
    suspend fun addProductToUser(product: Product, sender: String)
    suspend fun getAllUserProducts(email: String, getBooks: (List<Product>) -> Unit)
}
