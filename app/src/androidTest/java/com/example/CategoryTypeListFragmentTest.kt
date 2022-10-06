package com.example

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.commons.RecyclerViewMatcher
import com.example.zeldaapp.R
import com.example.zeldaapp.category.di.CategoryDi
import com.example.zeldaapp.category.presentation.type.CategoryTypeListFragment
import com.example.zeldaapp.category.presentation.type.CategoryTypeListViewModel
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class CategoryTypeListFragmentTest {

    @Before
    fun before() {
        loadKoinModules(CategoryDi.module)
    }

    @Test
    fun verifyShownList() {
        Thread.sleep(1_000)

        launchFragmentInContainer<CategoryTypeListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        Thread.sleep(1_000)

        CategoryTypeListViewModel().categoryList.value!!.forEachIndexed { index, type ->
            onView(
                RecyclerViewMatcher(R.id.rv_category_type).atPositionOnView(
                    index, R.id.tv_title
                )
            ).check(matches(withText(type)))
        }

        Thread.sleep(1_000)
    }

    @Test
    fun verifyClickInItem() {
        Thread.sleep(1_000)

        val typeScenario = launchFragmentInContainer<CategoryTypeListFragment>(
            themeResId = R.style.Theme_ZeldaApp
        )

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        typeScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        CategoryTypeListViewModel().categoryList.value!!.forEachIndexed { index, type ->
            onView(
                RecyclerViewMatcher(R.id.rv_category_type).atPositionOnView(
                    index, R.id.tv_title
                )
            ).perform(ViewActions.click())

            assertTrue(navController.currentDestination?.id == R.id.categoryItemsListFragment)
            typeScenario.onFragment {
                assertEquals(type, it.paramsVm.categoryName)
                navController.navigateUp()
            }

            Thread.sleep(1_000)
        }
    }

}