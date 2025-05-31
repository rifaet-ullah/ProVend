package com.personal.provend.domain.models

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val category: ProductCategory,
    val countInStock: Int,
)
