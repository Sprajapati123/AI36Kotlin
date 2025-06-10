package com.example.ai36.repository

import com.example.ai36.model.ProductModel

interface ProductRepository {

    //    {
//     "success" : true,
//    "message":"product fetched"
//    "product":"{ProductModel}"
//    }
    fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    )

    fun getAllProduct(
        callback: (Boolean, String, List<ProductModel?>) -> Unit
    )

    fun updateProduct(
        productId: String,
        data: MutableMap<String, Any?>,
        callback: (Boolean, String) -> Unit
    )

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    )


}