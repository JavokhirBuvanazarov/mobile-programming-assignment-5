package com.chunkingz.mdpquiz

data class Product(
    val productName: String,
    val productDescription: String,
    val cost: Double,
    val imageUrl: String,
    var isAddedToCart: Boolean = false // Default value is false
)

