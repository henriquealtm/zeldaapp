package com.example

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.commons.RecyclerViewMatcher
import com.example.di.InstrumentedCategoryDi
import com.example.mock.CategoryItemMock.categoryList
import com.example.zeldaapp.R
import com.example.zeldaapp.category.domain.usecase.CategoryItemListUseCase
import com.example.zeldaapp.category.presentation.CategoryParamsViewModel
import com.example.zeldaapp.category.presentation.item.list.CategoryItemListFragment
import com.example.zeldaapp.category.presentation.item.list.CategoryItemListViewModel
import com.google.android.material.appbar.MaterialToolbar
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

// TODO Henrique - In order to avoid mockk problems to create mocks here,
// TODO Henrique - it's being used the version 1.12.5

// TODO Henrique - There no current way to simulate the loading scenarios without
// TODO Henrique - resource idling which is not implemented here yet :(

@RunWith(AndroidJUnit4::class)
class CategoryItemListFragmentTest {

    private var categoryItemListUseCase = mockk<CategoryItemListUseCase>(relaxed = true)
    private var mockVm = mockk<CategoryParamsViewModel>(relaxed = true)
    private lateinit var itemListMockVm: CategoryItemListViewModel

    @Test
    fun verifyErrorIsDisplayedAndSuccessContainerIsHidden() {
        loadErrorModule()

        launchFragmentInContainer<CategoryItemListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        Thread.sleep(1000)

        onView(withId(R.id.ll_error_container)).check(matches(isDisplayed()))
        onView(withId(R.id.cl_success_container)).check(matches(not(isDisplayed())))
    }

    @Test
    fun verifySuccessContainerIsDisplayedAndErrorIsHidden() {
        loadSuccessModule()

        launchFragmentInContainer<CategoryItemListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        Thread.sleep(1000)

        onView(withId(R.id.ll_error_container)).check(matches(not(isDisplayed())))
        onView(withId(R.id.cl_success_container)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyTitle() {
        loadSuccessModule()

        launchFragmentInContainer<CategoryItemListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        Thread.sleep(1_000)

        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(MaterialToolbar::class.java))
            )
        ).check(matches(withText("Monster list")))

        Thread.sleep(1_000)
    }

    @Test
    fun verifyBtnSearchBtnCleanHidden() {
        loadSuccessModule()

        launchFragmentInContainer<CategoryItemListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        onView(withId(R.id.btn_search)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_clear)).check(matches(not(isDisplayed())))

        Thread.sleep(1_000)
    }

    @Test
    fun verifyBtnSearchHiddenBtnCleanDisplayed() {
        loadSuccessModule()

        launchFragmentInContainer<CategoryItemListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        onView(withId(R.id.et_search)).perform(typeText("123"))

        Thread.sleep(1_000)

        onView(withId(R.id.btn_search)).check(matches(not(isDisplayed())))
        onView(withId(R.id.btn_clear)).check(matches(isDisplayed()))

        Thread.sleep(1_000)
    }

    @Test
    fun verifyRecyclerViewItemsAndInteractionWithFilter() {
        loadSuccessModule()

        launchFragmentInContainer<CategoryItemListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        Thread.sleep(1_000)

        // Verify if list items are properly loaded in the recyclerview's items
        categoryList.forEachIndexed { index, categoryItem ->
            onView(
                RecyclerViewMatcher(R.id.rv_category_item).atPositionOnView(
                    index, R.id.tv_name
                )
            ).check(matches(withText(categoryItem.name)))
            onView(
                RecyclerViewMatcher(R.id.rv_category_item).atPositionOnView(
                    index, R.id.tv_description
                )
            ).check(matches(withText(categoryItem.description)))
        }

        Thread.sleep(1_000)

        // Verify if the list size is equal to the mocked list
        onView(withId(R.id.rv_category_item)).check(matches(hasChildCount(3)))

        onView(withId(R.id.et_search)).perform(typeText("bladed"))

        Thread.sleep(1_000)

        // Verify if the list size is equal to one after filtering the name bladed
        onView(withId(R.id.rv_category_item)).check(matches(hasChildCount(1)))

        Thread.sleep(1_000)

        onView(withId(R.id.btn_clear)).perform(click())

        Thread.sleep(1_000)

        // Verify if the list size is equal to the mocked list after clicking in the "clear filter" button
        onView(withId(R.id.rv_category_item)).check(matches(hasChildCount(3)))
    }

    // Load Koin Modules
    private fun loadSuccessModule() {
        coEvery { categoryItemListUseCase(any()) } returns categoryList
        itemListMockVm = CategoryItemListViewModel("Monster", categoryItemListUseCase)
        loadKoinModules(InstrumentedCategoryDi.module(mockVm, itemListMockVm))
    }

    private fun loadErrorModule() {
        coEvery { categoryItemListUseCase(any()) } throws java.lang.RuntimeException()
        itemListMockVm = CategoryItemListViewModel("Monster", categoryItemListUseCase)
        loadKoinModules(InstrumentedCategoryDi.module(mockVm, itemListMockVm))
    }

}
