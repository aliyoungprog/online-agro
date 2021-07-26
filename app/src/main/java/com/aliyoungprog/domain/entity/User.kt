package com.aliyoungprog.domain.entity

data class User(
    val email: String? = null,
    val password: String? = null,
    val myProducts: List<Product>? = null
    )
