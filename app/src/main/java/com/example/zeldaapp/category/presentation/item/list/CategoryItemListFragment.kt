package com.example.zeldaapp.category.presentation.item.list

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.navigation.fragment.findNavController
import com.example.commons.base.BaseFragment
import com.example.commons.extension.observeAndNavigateBack
import com.example.zeldaapp.R
import com.example.zeldaapp.category.presentation.CategoryParamsViewModel
import com.example.zeldaapp.databinding.FragmentCategoryItemListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryItemListFragment : BaseFragment<FragmentCategoryItemListBinding>(
    R.layout.fragment_category_item_list
) {

    @VisibleForTesting
    val categoryParamsVm: CategoryParamsViewModel by sharedViewModel()
    private val categoryItemListVm: CategoryItemListViewModel by viewModel {
        parametersOf(categoryParamsVm.categoryName)
    }

    private val categoryItemAdapter = CategoryItemListAdapter { categoryItem ->
        categoryParamsVm.categoryItem = categoryItem
        findNavController().navigate(CategoryItemListFragmentDirections.goToCategoryItemDetails())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvCategoryItem.adapter = categoryItemAdapter
    }

    override fun setupViewModel() {
        binding.vm = categoryItemListVm
        lifecycle.addObserver(categoryItemListVm)
    }

    override fun setupObservers() {
        observeAndNavigateBack(categoryItemListVm.navigateBack)
        observeList()
    }

    private fun observeList() {
        categoryItemListVm.list.observe(viewLifecycleOwner) { list ->
            categoryItemAdapter.submitList(list)
        }
    }

}