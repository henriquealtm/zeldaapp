package com.example.zeldaapp.presentation.item.list

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.example.zeldaapp.CoroutinesMainTestRule
import com.example.zeldaapp.category.domain.model.CategoryItem
import com.example.zeldaapp.category.domain.usecase.CategoryItemListUseCase
import com.example.zeldaapp.category.presentation.item.list.CategoryItemListViewModel
import com.example.zeldaapp.drawables
import com.example.zeldaapp.presentation.item.mock.CategoryItemMock.categoryList
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoryItemListViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesMainTestRule()

    private val categoryNameMock = "Monster"
    private var categoryItemListUseCase = mockk<CategoryItemListUseCase>(relaxed = true)
    private lateinit var categoryItemListVm: CategoryItemListViewModel

    private val searchValueObserver = Observer<String> {}
    private val searchIconObserver = Observer<Int> {}
    private val resourceList = Observer<List<CategoryItem>?> {}
    private val listObserver = Observer<List<CategoryItem>> {}

    @Before
    fun prepare() {
        categoryItemListVm = CategoryItemListViewModel(
            categoryNameMock,
            categoryItemListUseCase
        )
        prepareObservers()
    }

    private fun prepareObservers() {
        categoryItemListVm.searchValue.observeForever(searchValueObserver)
        categoryItemListVm.searchIcon.observeForever(searchIconObserver)
        categoryItemListVm.resourceList.data.observeForever(resourceList)
        categoryItemListVm.list.observeForever(listObserver)

    }

    @After
    fun cleanUp() {
        cleanUpObservers()
    }

    private fun cleanUpObservers() {
        categoryItemListVm.searchValue.removeObserver(searchValueObserver)
        categoryItemListVm.searchIcon.removeObserver(searchIconObserver)
        categoryItemListVm.resourceList.data.removeObserver(resourceList)
        categoryItemListVm.list.removeObserver(listObserver)
    }

    // Search - Section
    @Test
    fun `GIVEN the initial state of CategoryItemListViewModel THEN searchValue_value is null`() {
        assertNull(categoryItemListVm.searchValue.value)
    }

    @Test
    fun `GIVEN that searchValue_value is different from null WHEN onClearButtonClick() is called THEN searchValue_value receives an empty string`() {
        categoryItemListVm.run {
            completeList = categoryList
            searchValue.value = "Armored"
            onClearButtonClick()
            assertEquals("", searchValue.value)
        }
    }

    @Test
    fun `GIVEN the initial state of CategoryItemListViewModel THEN searchIcon_value is equal to ic_search`() {
        assertEquals(drawables.ic_search, categoryItemListVm.searchIcon.value)
    }

    @Test
    fun `GIVEN that searchValue_value is empty THEN searchIcon_value is equal to ic_search`() {
        categoryItemListVm.run {
            completeList = categoryList
            searchValue.value = "Armored"
            searchValue.value = ""
            assertEquals(drawables.ic_search, searchIcon.value)
        }
    }

    @Test
    fun `GIVEN that searchValue_value is different from null THEN searchIcon_value is equal to ic_close`() {
        categoryItemListVm.run {
            completeList = categoryList
            searchValue.value = "Armored"
            assertEquals(drawables.ic_close, searchIcon.value)
        }
    }

    // Resource - Section
    @Test
    fun `GIVEN the initial state of CategoryItemListViewModel THEN resourceList_value is null`() {
        assertNull(categoryItemListVm.resourceList.data.value)
    }

    @Test
    fun `WHEN resourceList_value is null THEN list_value is null`() {
        categoryItemListVm.run { assertNull(list.value) }
    }

    @Test
    fun `WHEN calling onCreate() THEN the CategoryItemListUseCase is called by the resourceList`() {
        categoryItemListVm.run {
            val lifecycleOwner = mockk<LifecycleOwner>()
            onCreate(lifecycleOwner)
            verify { resourceList.loadData() }
        }
    }

    @Test
    fun `WHEN calling onCreate() THEN the list_value and completedList are equal to the resource_list_data`() {
        categoryItemListVm.run {
            coEvery { categoryItemListUseCase(any()) } returns categoryList
            val lifecycleOwner = mockk<LifecycleOwner>()
            onCreate(lifecycleOwner)
            assertEquals(categoryList, list.value)
            assertEquals(categoryList, completeList)
        }
    }

    @Test
    fun `GIVEN the list_value has two items WHEN search_value receives a value that match just the first item THEN the list_value will have just one item`() {
        categoryItemListVm.run {
            coEvery { categoryItemListUseCase(any()) } returns categoryList
            val lifecycleOwner = mockk<LifecycleOwner>()
            onCreate(lifecycleOwner)
            searchValue.value = "armored"
            assertEquals(1, list.value?.size)
        }
    }

    @Test
    fun `WHEN calling tryAgain() THEN the CategoryItemListUseCase is called by the resourceList`() {
        categoryItemListVm.run {
            tryAgain()
            verify { resourceList.loadData() }
        }
    }

    @Test
    fun `Verify if CategoryItemListViewModel extends DefaultLifeCycleObserver`() {
        val mockedVm = mockk<CategoryItemListViewModel>(relaxUnitFun = true)

        val lifecycleOwner = mockk<LifecycleOwner>()
        val lifecycle = LifecycleRegistry(mockk())
        every { lifecycleOwner.lifecycle } returns lifecycle

        lifecycle.addObserver(mockedVm)
        lifecycle.markState(Lifecycle.State.INITIALIZED)
        verify(exactly = 0) { mockedVm.onCreate(any()) }

        lifecycle.currentState = Lifecycle.State.CREATED
        verify { mockedVm.onCreate(any()) }
    }

}