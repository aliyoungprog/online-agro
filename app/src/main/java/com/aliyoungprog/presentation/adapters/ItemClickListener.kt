package com.aliyoungprog.presentation.adapters

import com.aliyoungprog.domain.entity.Product

interface ItemClickListener {
    fun onItemClicked(product: Product)
}
