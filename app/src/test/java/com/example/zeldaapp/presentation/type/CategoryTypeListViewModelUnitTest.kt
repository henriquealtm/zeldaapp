package com.example.zeldaapp.presentation.type

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zeldaapp.CoroutinesMainTestRule
import com.example.zeldaapp.category.presentation.type.CategoryTypeListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoryTypeListViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesMainTestRule()

    private lateinit var categoryTypeListVm: CategoryTypeListViewModel

    @Before
    fun prepare() {
        categoryTypeListVm = CategoryTypeListViewModel()
    }

    // Search Value
    @Test
    fun `GIVEN the initial state of CategoryTypeListViewModel THEN categoryList has 5 proper categories`() {
        assertEquals(
            listOf(
                "Creatures",
                "Monsters",
                "Materials",
                "Equipment",
                "Treasure"
            ),
            categoryTypeListVm.categoryList.value
        )
    }

}

