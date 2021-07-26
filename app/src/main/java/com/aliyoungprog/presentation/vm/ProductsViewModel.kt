package com.aliyoungprog.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliyoungprog.domain.ProductRepository
import com.aliyoungprog.domain.entity.Product
import kotlinx.coroutines.launch

class ProductsViewModel(val repository: ProductRepository): ViewModel() {
    // Can talk only with repository

    val productsLiveData = MutableLiveData<List<Product>>()
    val allUserProducts = MutableLiveData<List<Product>>()

    fun getAllProducts(){
        viewModelScope.launch {
            try{
                repository.getAllProducts {
                    if (it.isEmpty())
                        productsLiveData.value = arrayListOf()
                    else
                        productsLiveData.value = it
                }
            }catch(e: Exception){
                //
            }
        }
    }


    fun getAllUserProducts(email: String){
        viewModelScope.launch {
            try {
                repository.getAllUserProducts(email) {
                    if (it.isEmpty())
                        allUserProducts.value = arrayListOf()
                    else
                        allUserProducts.value = it
                }
            } catch (e: Exception){

            }
        }
    }
}
