package com.example.mgokcafeteria.models

data class MenuItem(
    val id: Int,
    val name: String,
    val proteins: Double?,
    val fats: Double?,
    val carbohydrates: Double?,
    val calories: Double?,
    val weight: Double?,
    val ingredients: String?,
    val price: Double?,
    val categoryId: Int,
    val imagePath: String?
)
