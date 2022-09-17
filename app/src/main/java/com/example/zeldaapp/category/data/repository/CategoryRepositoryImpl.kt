package com.example.zeldaapp.category.data.repository

import com.example.zeldaapp.category.data.service.CategoryService

class CategoryRepositoryImpl(
    private val categoryService: CategoryService
) : ICategoryRepository {

    override suspend fun getCategoryItemList(
        category: String
    ) = categoryService.getCategoryItemList(category).data

    override suspend fun getCreaturesItemList() = categoryService.getCreaturesItemList().data

}