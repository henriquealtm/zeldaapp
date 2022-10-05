package com.example.zeldaapp.category.data.service

import com.example.zeldaapp.category.data.model.CategoryItemListResponse
import com.example.zeldaapp.category.data.model.CreaturesItemListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryService {

    @GET("category/{name}")
    suspend fun getCategoryItemList(
        @Path("name") name: String,
    ): CategoryItemListResponse

    @GET("category/creatures")
    suspend fun getCreaturesItemList(): CreaturesItemListResponse

}