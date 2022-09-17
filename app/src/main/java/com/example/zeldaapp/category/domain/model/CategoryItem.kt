package com.example.zeldaapp.category.domain.model

data class CategoryItem(
    val id: Int,
    val name: String,
    val category: String,
    val description: String,
    val image: String,
    val commonLocations: List<String>,
)