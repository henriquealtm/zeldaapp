package com.example.zeldaapp.category.domain.usecase

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

    private fun isCreatureCategory(category: String) = category.lowercase() == "creatures"

    private suspend fun getCategoryItemList(category: String) = repository
        .getCategoryItemList(category)
        .toCategoryItemList()
        .sortedBy { it.name }

    private suspend fun getCreaturesItemList() = repository
        .getCreaturesItemList()
        .toCategoryItemList()
        .sortedBy { it.name }

}