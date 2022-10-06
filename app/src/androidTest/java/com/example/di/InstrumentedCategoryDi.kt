package com.example.di

import com.example.network.getRetrofit
import com.example.zeldaapp.category.data.repository.CategoryRepositoryImpl
import com.example.zeldaapp.category.data.repository.ICategoryRepository
import com.example.zeldaapp.category.data.service.CategoryService
import com.example.zeldaapp.category.domain.usecase.CategoryItemListUseCase
import com.example.zeldaapp.category.presentation.CategoryParamsViewModel
import com.example.zeldaapp.category.presentation.item.detail.CategoryItemDetailsViewModel
import com.example.zeldaapp.category.presentation.item.list.CategoryItemListViewModel
import com.example.zeldaapp.category.presentation.type.CategoryTypeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object InstrumentedCategoryDi {

    fun module(
        paramVm: CategoryParamsViewModel
    ) = module {
        viewModel { paramVm }
        viewModel { CategoryTypeListViewModel() }
        viewModel { params ->
            CategoryItemListViewModel(
                categoryName = params.get(),
                useCase = get()
            )
        }
        viewModel { params ->
            CategoryItemDetailsViewModel(
                categoryItem = params.get(),
            )
        }

        factory { CategoryItemListUseCase(repository = get()) }

        single<ICategoryRepository> { CategoryRepositoryImpl(get()) }

        single { getRetrofit().create(CategoryService::class.java) }
    }

}