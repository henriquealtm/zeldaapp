package com.example.zeldaapp.category.domain.mapper

import com.example.commons.extension.handleOpt
import com.example.zeldaapp.category.data.model.CreaturesItemListDto
import com.example.zeldaapp.category.domain.model.CategoryItem

fun CreaturesItemListDto.toCategoryItemList(): List<CategoryItem> =
    mutableListOf<CategoryItem>().apply {
        addAll(food?.toCategoryItemList().handleOpt())
        addAll(nonFood?.toCategoryItemList().handleOpt())
    }