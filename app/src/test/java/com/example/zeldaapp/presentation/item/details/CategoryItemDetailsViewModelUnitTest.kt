package com.example.zeldaapp.presentation.item.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zeldaapp.CoroutinesMainTestRule
import com.example.zeldaapp.category.presentation.item.detail.CategoryItemDetailsViewModel
import com.example.zeldaapp.presentation.item.mock.CategoryItemMock.categoryItemWithMultipleLocations
import com.example.zeldaapp.presentation.item.mock.CategoryItemMock.categoryItemWithNoCommonLocations
import com.example.zeldaapp.presentation.item.mock.CategoryItemMock.categoryItemWithOneLocation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CategoryItemDetailsViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = CoroutinesMainTestRule()

    @Test
    fun `WHEN categoryItem_commonLocations is an emptyList THEN formattedCommonLocations is an empty String`() {
        val categoryItemsListVm = CategoryItemDetailsViewModel(categoryItemWithNoCommonLocations)
        assertTrue(categoryItemsListVm.formattedCommonLocations.isEmpty())
    }

    @Test
    fun `WHEN formattedCommonLocations is empty THEN showCommonLocationsSection is false`() {
        val categoryItemsListVm = CategoryItemDetailsViewModel(categoryItemWithNoCommonLocations)
        assertTrue(categoryItemsListVm.formattedCommonLocations.isEmpty())
    }

    @Test
    fun `WHEN formattedCommonLocations has one item THEN formattedCommonLocations has value without comma`() {
        val categoryItemsListVm = CategoryItemDetailsViewModel(categoryItemWithOneLocation)
        assertEquals(
            "Lanayru Great Spring",
            categoryItemsListVm.formattedCommonLocations
        )
    }

    @Test
    fun `WHEN formattedCommonLocations has more than one item THEN formattedCommonLocations has string values separated by comma`() {
        val categoryItemsListVm = CategoryItemDetailsViewModel(categoryItemWithMultipleLocations)
        assertEquals(
            "Hyrule Field, Hebra Mountains, East Necluda",
            categoryItemsListVm.formattedCommonLocations
        )
    }

}