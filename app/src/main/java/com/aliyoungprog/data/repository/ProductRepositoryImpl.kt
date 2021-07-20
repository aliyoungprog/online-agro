package com.aliyoungprog.data.repository

import com.aliyoungprog.data.database.FirestoreDb
import com.aliyoungprog.domain.ProductRepository
import com.aliyoungprog.domain.entity.Product
import com.aliyoungprog.domain.entity.User
import com.google.firebase.firestore.FieldValue
import timber.log.Timber
import java.lang.Exception

class ProductRepositoryImpl: ProductRepository {

    // This class should only implement Repository;
    override suspend fun getAllProducts(success : (List<Product>)->Unit){
        val d = FirestoreDb.db_firestore.collection("products")
        val books = arrayListOf<Product>()
        d.get().addOnSuccessListener {
            for (x in it){
                if (x != null) {
                    val product: Product = x.toObject(Product::class.java)
                    books.add(product)
                }
            }
            success(books)
        }
        d.get().addOnFailureListener {
            throw Exception(it)
        }
    }

    override suspend fun addProductToUser(product: Product, sender: String) {
            FirestoreDb.db_firestore.collection("users").document(sender)
                .update("myProducts", FieldValue.arrayUnion(product))
    }

    override suspend fun getAllUserProducts(email: String, getBooks: (List<Product>) -> Unit) {
        FirestoreDb.db_firestore.collection("users").document(email).get().addOnSuccessListener{
            val list = it.toObject(User::class.java)?.myProducts
            Timber.tag("size of the list").i("$list")
            if (list != null) {
                getBooks(list)
            }else{
                getBooks(listOf())
            }
        }
    }
}
