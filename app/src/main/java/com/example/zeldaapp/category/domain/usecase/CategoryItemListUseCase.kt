package com.example.zeldaapp.category.domain.usecase

import androidx.annotation.VisibleForTesting
import com.example.zeldaapp.category.data.repository.ICategoryRepository
import com.example.zeldaapp.category.domain.mapper.toCategoryItemList

class CategoryItemListUseCase(
    private val repository: ICategoryRepository
) {

    suspend operator fun invoke(
        category: String
    ) = if (isCreatureCategory(category)) {
        getCreaturesItemList()
    } else {
        getCategoryItemList(category.lowercase())
    }

    @VisibleForTesting
    fun isCreatureCategory(category: String) = category.lowercase() == "creatures"

    @VisibleForTesting
    suspend fun getCategoryItemList(category: String) = repository
        .getCategoryItemList(category)
        .toCategoryItemList()
        .sortedBy { it.name }

    @VisibleForTesting
    suspend fun getCreaturesItemList() = repository
        .getCreaturesItemList()
        .toCategoryItemList()
        .sortedBy { it.name }

}