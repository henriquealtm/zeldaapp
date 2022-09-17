package com.example.zeldaapp.category.domain.mapper

import com.example.zeldaapp.category.data.model.CreaturesItemListDto
import com.example.zeldaapp.category.domain.model.CategoryItem

fun CreaturesItemListDto.toCategoryItemList(): List<CategoryItem> =
    mutableListOf<CategoryItem>().apply {
        addAll(food.toCategoryItemList())
        addAll(nonFood.toCategoryItemList())
    }