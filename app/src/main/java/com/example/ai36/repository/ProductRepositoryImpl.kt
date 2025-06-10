package com.example.ai36.repository

import com.example.ai36.model.ProductModel
import com.google.firebase.database.FirebaseDatabase

class ProductRepositoryImpl : ProductRepository {
    val database = FirebaseDatabase.getInstance()
    val ref = database.reference.child("products")

    //add -> setValue()
    //update -> updateChildren()
    //delete -> removeValue()
    override fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        val id = ref.push().key.toString()
        model.productId = id
        ref.child(id).setValue(model).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Product added")
            }else{
                callback(false,"${it.exception?.message}")

            }
        }
    }

    override fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getAllProduct(callback: (Boolean, String, List<ProductModel?>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun updateProduct(
        productId: String,
        data: MutableMap<String, Any?>,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}