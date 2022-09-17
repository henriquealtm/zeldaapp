package com.example.zeldaapp.category.presentation.item.detail

import com.example.commons.base.BaseFragment
import com.example.commons.extension.observeAndNavigateBack
import com.example.zeldaapp.R
import com.example.zeldaapp.category.presentation.CategoryParamsViewModel
import com.example.zeldaapp.databinding.FragmentCategoryItemDetailsBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryItemDetailsFragment : BaseFragment<FragmentCategoryItemDetailsBinding>(
    R.layout.fragment_category_item_details
) {

    private val categoryParamsVm: CategoryParamsViewModel by sharedViewModel()
    private val categoryItemListVm: CategoryItemDetailsViewModel by viewModel {
        parametersOf(categoryParamsVm.categoryItem)
    }

    override fun setupViewModel() {
        binding.vm = categoryItemListVm
    }

    override fun setupObservers() {
        observeAndNavigateBack(categoryItemListVm.navigateBack)
    }

}