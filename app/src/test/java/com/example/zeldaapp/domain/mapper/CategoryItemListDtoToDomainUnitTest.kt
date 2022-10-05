package com.example.zeldaapp.domain.mapper

import com.example.zeldaapp.category.data.model.CategoryItemDto
import com.example.zeldaapp.category.domain.mapper.toCategoryItem
import com.example.zeldaapp.category.domain.mapper.toCategoryItemList
import com.example.zeldaapp.category.domain.model.CategoryItem
import com.example.zeldaapp.domain.mock.CategoryItemDtoMock
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CategoryItemListDtoToDomainUnitTest {

    @Test
    fun `WHEN calling CategoryItemDto_toCategoryItem() with null properties THEN returns an CategoryItem initialized`() =
        runBlocking {
            val nullPropertiesCategoryItemDto = CategoryItemDto(
                id = null,
                name = null,
                category = null,
                description = null,
                image = null,
                commonLocations = null,
            )
            val initializedCategoryItem = CategoryItem(
                id = 0,
                name = "",
                category = "",
                description = "123",
                image = "",
                commonLocations = listOf(),
            )
            Assert.assertEquals(
                initializedCategoryItem,
                nullPropertiesCategoryItemDto.toCategoryItem()
            )
        }

    @Test
    fun `WHEN calling CategoryItemDto_toCategoryItem() THEN returns an CategoryItem with the proper values and capital letters`() =
        runBlocking {
            val categoryItem = CategoryItem(
                id = 789,
                name = "Bladed rhino",
                category = "Creatures",
                description = "This beetle's ...",
                image = "",
                commonLocations = listOf("Hyrule Field", "Hebra Mountains", "East Necluda")
            )
            Assert.assertEquals(
                categoryItem,
                CategoryItemDtoMock.categoryItemDtoTwo.toCategoryItem()
            )
        }

    @Test
    fun `WHEN calling List_CategoryItemDto_toCategoryItemList() with an empty list THEN returns a CateogyItem empty list`() =
        runBlocking {
            Assert.assertEquals(
                listOf<CategoryItem>(),
                listOf<CategoryItemDto>().toCategoryItemList()
            )
        }

    @Test
    fun `WHEN calling List_CategoryItemDto_toCategoryItemList() with a non empty list THEN returns a CateogyItem proper list`() =
        runBlocking {
            val expectedList = listOf(
                CategoryItem(
                    id = 123,
                    name = "White-maned lynel",
                    category = "Monsters",
                    description = "These fearsome monsters have lived in Hyrule since ancient times. Their ability to breathe fire makes White-Maned Lynels among the toughest of the species; each one of their attacks is an invitation to the grave. There are so few eyewitness accounts of this breed because a White-Maned Lynel is not one to let even simple passersby escape with their lives.",
                    image = "https://botw-compendium.herokuapp.com/api/v2/entry/white-maned_lynel/image",
                    commonLocations = listOf()
                ),
                CategoryItem(
                    id = 789,
                    name = "Bladed rhino",
                    category = "Creatures",
                    description = "This beetle's ...",
                    image = "",
                    commonLocations = listOf("Hyrule Field", "Hebra Mountains", "East Necluda")
                ),
                CategoryItem(
                    id = 456,
                    name = "Armored carp",
                    category = "Creatures",
                    description = "Calcium deposits...",
                    image = "",
                    commonLocations = listOf("Lanayru Great Spring")
                )
            )
            
            Assert.assertEquals(
                expectedList,
                CategoryItemDtoMock.list.toCategoryItemList()
            )
        }

}