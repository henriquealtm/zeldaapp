package com.example.zeldaapp.category.presentation.item.detail

import com.example.commons.base.BaseViewModel
import com.example.zeldaapp.category.domain.model.CategoryItem
import java.lang.String.join

class CategoryItemDetailsViewModel(
    val categoryItem: CategoryItem
) : BaseViewModel() {

    val formattedCommonLocations: String = join(", ", categoryItem.commonLocations)

}