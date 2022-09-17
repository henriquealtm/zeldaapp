package com.example.zeldaapp.category.presentation

import androidx.lifecycle.ViewModel
import com.example.zeldaapp.category.domain.model.CategoryItem

class CategoryParamsViewModel : ViewModel() {

    lateinit var categoryName: String
    lateinit var categoryItem: CategoryItem

}