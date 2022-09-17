package com.example.zeldaapp.category.data.repository

import com.example.zeldaapp.category.data.model.CategoryItemDto
import com.example.zeldaapp.category.data.model.CreaturesItemListDto

interface ICategoryRepository {

    suspend fun getCategoryItemList(
        category: String
    ): List<CategoryItemDto>

    suspend fun getCreaturesItemList(): CreaturesItemListDto


}