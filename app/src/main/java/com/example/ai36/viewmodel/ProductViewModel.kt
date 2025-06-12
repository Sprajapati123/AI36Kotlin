package com.example.ai36.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ai36.model.ProductModel
import com.example.ai36.repository.ProductRepository

class ProductViewModel(val repo: ProductRepository) : ViewModel() {

    fun uploadImage(context: Context,imageUri: Uri, callback: (String?) -> Unit){
        repo.uploadImage(context,imageUri,callback)
    }

    fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.addProduct(model, callback)
    }

    private val _products = MutableLiveData<ProductModel?>()
    val products: LiveData<ProductModel?> get() = _products

    fun getProductById(
        productId: String,
    ) {
        repo.getProductById(productId) { success, message, data ->
            if (success) {
                _products.postValue(data)
            } else {
                _products.postValue(null)

            }
        }
    }

    private val _allProducts = MutableLiveData<List<ProductModel?>>()
    val allProducts: LiveData<List<ProductModel?>> get() = _allProducts

    var _loading = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
        get() = _loading

    fun getAllProduct() {
        _loading.postValue(true)
        repo.getAllProduct { success, msg, data ->
            if (success) {
                _allProducts.postValue(data)
                _loading.postValue(false)
            } else {
                _allProducts.postValue(emptyList())
                _loading.postValue(false)
            }
        }
    }

    fun updateProduct(
        productId: String,
        data: MutableMap<String, Any?>,
        callback: (Boolean, String) -> Unit
    ) {
        repo.updateProduct(productId, data, callback)
    }

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        repo.deleteProduct(productId, callback)
    }
}