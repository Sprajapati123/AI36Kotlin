package com.example.ai36.model

data class ProductModel(
    var productId: String = "",
    var productName: String = "",
    var price: Double = 0.0,
    var quantity: Int = 0,
    var description: String = "",
    var categoryId: String = "",
    var isActive: Boolean = false,
)
