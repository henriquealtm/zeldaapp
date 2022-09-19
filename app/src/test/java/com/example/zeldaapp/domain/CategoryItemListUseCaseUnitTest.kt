package com.example.zeldaapp.domain

import com.example.zeldaapp.category.data.repository.ICategoryRepository
import com.example.zeldaapp.category.domain.mapper.toCategoryItemList
import com.example.zeldaapp.category.domain.model.CategoryItem
import com.example.zeldaapp.category.domain.usecase.CategoryItemListUseCase
import com.example.zeldaapp.domain.mock.CategoryItemDtoMock
import io.mockk.coEvery
import io.mockk.coVerify
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
    fun `GIVEN category equals to "creatures" WHEN calling isCreatureCategory() THEN returns true`() {
        assertTrue(categoryItemListUseCase.isCreatureCategory("creatures"))
    }

    @Test
    fun `GIVEN category different from "creatures" WHEN calling isCreatureCategory() THEN returns false`() {
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
    fun `WHEN calling the useCase and isCreatureCategory() returns true THEN getCreaturesItemList() must be called`() =
        runBlocking {
            val mockedUseCase = spyk(categoryItemListUseCase)
            coEvery { mockedUseCase.getCreaturesItemList() } returns emptyList()
            coEvery { mockedUseCase.isCreatureCategory(any()) } returns true
            mockedUseCase("")
            coVerify { mockedUseCase.getCreaturesItemList() }
        }

    @Test
    fun `WHEN calling the useCase and isCreatureCategory() returns false THEN getCreaturesItemList() must be called`() =
        runBlocking {
            val mockedUseCase = spyk(categoryItemListUseCase)
            coEvery { mockedUseCase.getCreaturesItemList() } returns emptyList()
            coEvery { mockedUseCase.isCreatureCategory(any()) } returns false
            mockedUseCase("")
            coVerify { mockedUseCase.getCategoryItemList(any()) }
        }

}