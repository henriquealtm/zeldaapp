package com.example.zeldaapp.category.data.model

import com.example.network.NetworkData
import com.google.gson.annotations.SerializedName

typealias CreaturesItemListResponse = NetworkData<CreaturesItemListDto>

data class CreaturesItemListDto(
    val food: List<CategoryItemDto>?,
    @SerializedName("non_food")
    val nonFood: List<CategoryItemDto>?,
)