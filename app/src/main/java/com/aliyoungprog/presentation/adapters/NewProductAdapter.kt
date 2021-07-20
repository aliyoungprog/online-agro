package com.aliyoungprog.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliyoungprog.R
import com.aliyoungprog.domain.entity.Product

class NewProductAdapter(var products: List<Product> = listOf(), private val clickListener: ItemClickListener): RecyclerView.Adapter<SingleProductViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
        return SingleProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SingleProductViewHolder, position: Int) {
        holder.bind(products[position], clickListener)
    }

    override fun getItemCount(): Int = products.size
}

