package com.aliyoungprog.domain.entity

data class User(
    val email: String? = null,
    val photo_url: String? = null,
    val password: String? = null,
    val instagram_account: String? = null,
    val telegram_account: String? = null,
    val myProducts: List<Product>? = null
    )
