package com.aliyoungprog.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliyoungprog.domain.ProductRepository
import com.aliyoungprog.domain.entity.Product
import kotlinx.coroutines.launch
import timber.log.Timber


class ProductsViewModel(val repository: ProductRepository): ViewModel() {
    // Can talk only with repository

    val booksLiveData = MutableLiveData<List<Product>>()
    val allUserBook = MutableLiveData<List<Product>>()

//    fun getAllBooks(){
//        viewModelScope.launch {
//            try{
//                repository.getAllProducts {
//                    if (it.isEmpty())
//                        booksLiveData.value = listOf()
//                    else
//                        booksLiveData.value = it
//                }
//            }catch(e: Exception){
//                //
//            }
//        }
//    }


    fun getAllUserBooks(email: String){
        viewModelScope.launch {
            try {
                repository.getAllUserProducts(email) {
                    Timber.tag("products amount").i("{$it.size}")
                    if (it.isEmpty())
                        allUserBook.value = listOf()
                    else
                        allUserBook.value = it
                }
            } catch (e: Exception){

            }
        }
    }
}
