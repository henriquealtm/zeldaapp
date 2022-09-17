package com.example.zeldaapp.category.presentation.item.list

import androidx.lifecycle.*
import com.example.commons.base.BaseViewModel
import com.example.commons.data.DataResource
import com.example.commons.data.resource
import com.example.zeldaapp.R
import com.example.zeldaapp.category.domain.model.CategoryItem
import com.example.zeldaapp.category.domain.usecase.CategoryItemListUseCase

class CategoryItemListViewModel(
    val categoryName: String,
    private val useCase: CategoryItemListUseCase,
) : BaseViewModel(), DefaultLifecycleObserver {

    // Search - Section
    val searchValue = MutableLiveData<String>()

    val searchIcon = MediatorLiveData<Int>().apply {
        addSource(searchValue) {
            value = if (it.isNullOrEmpty()) R.drawable.ic_search else R.drawable.ic_close
        }

        value = R.drawable.ic_search
    }

    fun onClearButtonClick() {
        searchValue.value = ""
    }

    // Resource - Section
    val resourceList: DataResource<List<CategoryItem>> = resource(viewModelScope) {
        useCase(categoryName)
    }

    lateinit var completeList: List<CategoryItem>

    val list = MediatorLiveData<List<CategoryItem>>().apply {
        addSource(searchValue) { searchValue ->
            value = completeList.filter {
                it.name.lowercase().contains(searchValue.lowercase())
            }
        }

        addSource(resourceList.data) { list ->
            list?.let {
                value = list
                completeList = list
            }
        }
    }

    fun tryAgain() {
        resourceList.loadData()
    }

    // On Create - Section
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        resourceList.loadData()
    }

}