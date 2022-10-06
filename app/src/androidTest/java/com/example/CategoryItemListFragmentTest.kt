package com.example

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.di.InstrumentedCategoryDi
import com.example.zeldaapp.R
import com.example.zeldaapp.category.presentation.CategoryParamsViewModel
import com.example.zeldaapp.category.presentation.item.list.CategoryItemListFragment
import com.google.android.material.appbar.MaterialToolbar
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

// TODO Henrique - In order to avoid mockk problems to create mocks here,
// TODO Henrique - it's being used the version 1.12.5

@RunWith(AndroidJUnit4::class)
class CategoryItemListFragmentTest {

    @Before
    fun before() {
        val mockVm = mockk<CategoryParamsViewModel>(relaxed = true)

        every { mockVm.categoryName } returns "Monster"

        loadKoinModules(
            InstrumentedCategoryDi.module(mockVm)
        )
    }

    @Test
    fun verifyTitleAndLoadingState() {
        launchFragmentInContainer<CategoryItemListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(isAssignableFrom(MaterialToolbar::class.java))
            )
        ).check(matches(withText("Monster list")))
        onView(withId(R.id.uil_category_item)).check(matches(isDisplayed()))

        Thread.sleep(1_000)
    }

}