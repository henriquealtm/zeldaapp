package com.example.zeldaapp.category.presentation.type

import androidx.annotation.VisibleForTesting
import androidx.navigation.fragment.findNavController
import com.example.commons.base.BaseFragment
import com.example.zeldaapp.R
import com.example.zeldaapp.category.presentation.CategoryParamsViewModel
import com.example.zeldaapp.databinding.FragmentCategoryTypeListBinding

import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryTypeListFragment : BaseFragment<FragmentCategoryTypeListBinding>(
    R.layout.fragment_category_type_list
) {

    @VisibleForTesting
    val paramsVm: CategoryParamsViewModel by sharedViewModel()
    @VisibleForTesting
    val categoryListVm: CategoryTypeListViewModel by viewModel()
    override fun setupViewModel() {
        binding.vm = categoryListVm
    }

    override fun setupObservers() {
        categoryListVm.categoryList.observe(viewLifecycleOwner) { list ->
            list?.let { setupAdapter(list) }
        }
    }

    private fun setupAdapter(list: List<String>) {
        binding.rvCategoryType.adapter = CategoryTypeAdapter(list) { categoryType ->
            onCategoryTypeOnClick(categoryType)
        }
    }

    private fun onCategoryTypeOnClick(categoryType: String) {
        paramsVm.categoryName = categoryType
        findNavController().navigate(
            CategoryTypeListFragmentDirections.goToCategoryItems()
        )
    }

}