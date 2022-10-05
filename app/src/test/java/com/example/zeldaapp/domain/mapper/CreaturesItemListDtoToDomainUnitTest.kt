package com.example.zeldaapp.domain.mapper

import com.example.zeldaapp.category.data.model.CreaturesItemListDto
import com.example.zeldaapp.category.domain.mapper.toCategoryItem
import com.example.zeldaapp.category.domain.mapper.toCategoryItemList
import com.example.zeldaapp.category.domain.model.CategoryItem
import com.example.zeldaapp.domain.mock.CategoryItemDtoMock
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CreaturesItemListDtoToDomainUnitTest {

    @Test
    fun `WHEN calling CreaturesItemListDto_toCategoryItemList() with null properties THEN returns a List_CategoryItem initialized`() =
        runBlocking {
            val nullPropertiesCreaturesItemListDto = CreaturesItemListDto(
                food = null,
                nonFood = null,
            )
            Assert.assertEquals(
                listOf<CategoryItem>(),
                nullPropertiesCreaturesItemListDto.toCategoryItemList()
            )
        }

    @Test
    fun `WHEN calling CreaturesItemListDto_toCategoryItem() with food and non food creatures THEN returns a list with the proper CategoryItems`() =
        runBlocking {
            val nullPropertiesCreaturesItemListDto = CreaturesItemListDto(
                food = listOf(CategoryItemDtoMock.categoryItemDtoOne),
                nonFood = listOf(CategoryItemDtoMock.categoryItemDtoThree),
            )
            Assert.assertEquals(
                listOf(
                    CategoryItemDtoMock.categoryItemDtoOne.toCategoryItem(),
                    CategoryItemDtoMock.categoryItemDtoThree.toCategoryItem()
                ),
                nullPropertiesCreaturesItemListDto.toCategoryItemList()
            )
        }

}