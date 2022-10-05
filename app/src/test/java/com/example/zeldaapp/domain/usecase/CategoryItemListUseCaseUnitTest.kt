package com.example.zeldaapp.domain.usecase

import com.example.zeldaapp.category.data.repository.ICategoryRepository
import com.example.zeldaapp.category.domain.mapper.toCategoryItemList
import com.example.zeldaapp.category.domain.model.CategoryItem
import com.example.zeldaapp.category.domain.usecase.CategoryItemListUseCase
import com.example.zeldaapp.domain.mock.CategoryItemDtoMock
import com.example.zeldaapp.presentation.item.mock.CategoryItemMock
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CategoryItemListUseCaseUnitTest {

    private var repository = mockk<ICategoryRepository>(relaxed = true)
    private lateinit var categoryItemListUseCase: CategoryItemListUseCase

    @Before
    fun prepare() {
        categoryItemListUseCase = CategoryItemListUseCase(repository)
    }

    // Is Creature Category - Section
    @Test
    fun `WHEN calling isCreatureCategory() passing 'creatures' as parameter THEN returns true`() {
        assertTrue(categoryItemListUseCase.isCreatureCategory("creatures"))
    }

    @Test
    fun `WHEN calling isCreatureCategory() passing anything else than 'creatures' as parameter THEN returns false`() {
        assertFalse(categoryItemListUseCase.isCreatureCategory("monsters"))
    }

    // Get Item List - Section
    @Test
    fun `WHEN repository_getCategoryItemList returns and empty list THEN getCategoryItemList() returns an empty list`() =
        runBlocking {
            coEvery { repository.getCategoryItemList(any()) } returns emptyList()
            assertEquals(listOf<CategoryItem>(), categoryItemListUseCase.getCategoryItemList(""))
        }

    @Test
    fun `WHEN repository_getCategoryItemList returns a list with three item THEN getCategoryItemList() returns the mapped and sorted list`() =
        runBlocking {
            coEvery { repository.getCategoryItemList(any()) } returns CategoryItemDtoMock.list
            assertEquals(
                CategoryItemDtoMock.list.toCategoryItemList().sortedBy { it.name },
                categoryItemListUseCase.getCategoryItemList("")
            )
        }

    // Get Creatures Item List - Section
    @Test
    fun `WHEN repository_getCreaturesItemList returns and empty list THEN getCategoryItemList() returns an empty list`() =
        runBlocking {
            coEvery { repository.getCreaturesItemList() } returns CategoryItemDtoMock.emptyCreaturesItemListDto
            assertEquals(listOf<CategoryItem>(), categoryItemListUseCase.getCreaturesItemList())
        }

    @Test
    fun `WHEN repository_getCreaturesItemList returns a list with three item THEN getCategoryItemList() returns the mapped and sorted list`() =
        runBlocking {
            coEvery { repository.getCreaturesItemList() } returns CategoryItemDtoMock.creaturesItemListDto
            assertEquals(
                CategoryItemDtoMock.creaturesItemListDto.toCategoryItemList().sortedBy { it.name },
                categoryItemListUseCase.getCreaturesItemList()
            )
        }

    // Invoke
    @Test
    fun `WHEN calling the useCase and isCreatureCategory() returns true THEN useCase returns the value returned from getCreaturesItemList()`() =
        runBlocking {
            val mockedUseCase = spyk(categoryItemListUseCase)
            coEvery { mockedUseCase.getCreaturesItemList() } returns CategoryItemMock.categoryList
            coEvery { mockedUseCase.isCreatureCategory(any()) } returns true
            assertEquals(
                CategoryItemMock.categoryList,
                mockedUseCase("")
            )
        }

    @Test
    fun `WHEN calling the useCase and isCreatureCategory() returns false THEN useCase returns the value returned from getCategoryItemList()`() =
        runBlocking {
            val mockedUseCase = spyk(categoryItemListUseCase)
            coEvery { mockedUseCase.getCategoryItemList(any()) } returns CategoryItemMock.categoryList
            coEvery { mockedUseCase.isCreatureCategory(any()) } returns false
            assertEquals(
                CategoryItemMock.categoryList,
                mockedUseCase("")
            )
        }

}