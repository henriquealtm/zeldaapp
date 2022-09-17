package com.example.zeldaapp.domain.mock

import com.example.zeldaapp.category.data.model.CategoryItemDto
import com.example.zeldaapp.category.data.model.CreaturesItemListDto

object CategoryItemDtoMock {

    val categoryItemDtoOne = CategoryItemDto(
        id = 123,
        name = "white-maned lynel",
        category = "monsters",
        description = "These fearsome monsters have lived in Hyrule since ancient times. Their ability to breathe fire makes White-Maned Lynels among the toughest of the species; each one of their attacks is an invitation to the grave. There are so few eyewitness accounts of this breed because a White-Maned Lynel is not one to let even simple passersby escape with their lives.",
        image = "https://botw-compendium.herokuapp.com/api/v2/entry/white-maned_lynel/image",
        commonLocations = listOf()
    )

    val categoryItemDtoTwo = CategoryItemDto(
        id = 789,
        name = "bladed rhino",
        category = "creatures",
        description = "This beetle's ...",
        image = "",
        commonLocations = listOf("Hyrule Field", "Hebra Mountains", "East Necluda")
    )

    val categoryItemDtoThree = CategoryItemDto(
        id = 456,
        name = "armored carp",
        category = "creatures",
        description = "Calcium deposits...",
        image = "",
        commonLocations = listOf("Lanayru Great Spring")
    )

    val list = listOf(
        categoryItemDtoOne,
        categoryItemDtoTwo,
        categoryItemDtoThree
    )


    val emptyCreaturesItemListDto = CreaturesItemListDto(emptyList(), emptyList())

    val creaturesItemListDto = CreaturesItemListDto(
        listOf(categoryItemDtoTwo),
        listOf(categoryItemDtoThree)
    )

}

