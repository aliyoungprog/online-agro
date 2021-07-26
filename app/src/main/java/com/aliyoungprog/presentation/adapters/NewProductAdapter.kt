package com.aliyoungprog.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.aliyoungprog.R
import com.aliyoungprog.domain.entity.Product
import java.util.*
import kotlin.collections.ArrayList

class NewProductAdapter(var products: ArrayList<Product> = listOf<Product>() as ArrayList<Product>, private val clickListener: ItemClickListener): RecyclerView.Adapter<SingleProductViewHolder>(){


    val initialFilteredList : ArrayList<Product>
        get() = ArrayList<Product>().apply { addAll(products) }
    val temp = products

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_product, parent, false)
        return SingleProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SingleProductViewHolder, position: Int) {
        holder.bind(products[position], clickListener)
    }

    override fun getItemCount(): Int = products.size

    private val productFilter = object : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<Product> = ArrayList()
            if (constraint == null || constraint.isEmpty()){
                initialFilteredList.let { filteredList.addAll(it) }
            }else{
                val query = constraint.toString().toLowerCase(Locale.ROOT)
                initialFilteredList.forEach{
                    if (it.category?.toLowerCase(Locale.ROOT)!!.contains(query)){
                        filteredList.add(it)
                    }
                }
            }
            val res = FilterResults()
            res.values = filteredList
            return res
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>){
                products.clear()
                products.addAll(results.values as ArrayList<Product>)
                notifyDataSetChanged()
            }
        }
    }

    fun getFilter(): Filter {
        return productFilter
    }
}

