package com.example.ai36.repository

import com.example.ai36.model.ProductModel
import com.example.ai36.model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
        ref.child(productId).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var products = snapshot.getValue(ProductModel::class.java)
                    if(products != null){
                        callback(true,"Fetched",products)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false,error.message,null)
            }

        })
    }

    override fun getAllProduct(callback: (Boolean, String, List<ProductModel?>) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val allProducts = mutableListOf<ProductModel>()
                if(snapshot.exists()){
                    for(eachData in snapshot.children){
                        var products = snapshot.getValue(ProductModel::class.java)
                        if(products != null){
                            allProducts.add(products)
                        }
                    }
                    callback(true,"fetched",allProducts)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false,error.message,emptyList())
            }

        })
    }

    override fun updateProduct(
        productId: String,
        data: MutableMap<String, Any?>,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).updateChildren(data).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Product updated")
            }else{
                callback(false,"${it.exception?.message}")

            }
        }
    }

    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).removeValue().addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Product deleted")
            }else{
                callback(false,"${it.exception?.message}")

            }
        }
    }
}