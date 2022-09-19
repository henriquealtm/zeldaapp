package com.example.zeldaapp.category.presentation.type

import androidx.lifecycle.MutableLiveData
import com.example.commons.base.BaseViewModel

class CategoryTypeListViewModel : BaseViewModel() {

    val categoryList = MutableLiveData(
        listOf(
            "Creatures",
            "Monsters",
            "Materials",
            "Equipment",
            "Treasure",
        )
    )

}