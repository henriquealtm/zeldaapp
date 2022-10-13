package com.example.zeldaapp.category.presentation.item.list

import androidx.annotation.VisibleForTesting
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
    val searchValue = MutableLiveData("")

    // These two states could be changed to just the Image Button Icon state, changing the icon
    // according to the searchValue values.
    // But there is no way to instrument test this icon change. So in order to instrument test it,
    // it was created this two states, because it's easy to instrument test the material button with
    // the isDisplayed() match.
    val isSearchIconVisible = searchValue.map { it.isNullOrEmpty() }

    val isClearIconVisible = searchValue.map { it.isNotEmpty() }

    fun onClearButtonClick() {
        searchValue.value = ""
    }

    // Resource - Section
    val resourceList: DataResource<List<CategoryItem>> = resource(viewModelScope) {
        useCase(categoryName)
    }

    @VisibleForTesting
    var completeList: List<CategoryItem>? = null

    val list = MediatorLiveData<List<CategoryItem>>().apply {
        addSource(searchValue) { searchValue ->
            value = completeList?.filter {
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