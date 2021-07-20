package com.aliyoungprog.domain.di

import com.aliyoungprog.data.repository.ProductRepositoryImpl
import com.aliyoungprog.data.repository.UserRepositoryImpl
import com.aliyoungprog.domain.ProductRepository
import com.aliyoungprog.domain.UserRepository
import com.aliyoungprog.presentation.vm.ProductsViewModel
import com.aliyoungprog.presentation.vm.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = module {
    single <ProductRepository> { ProductRepositoryImpl() }
    single <UserRepository> { UserRepositoryImpl() }
    viewModel {
        ProductsViewModel(get())
    }
    viewModel {
        UserViewModel(get())
    }
}
