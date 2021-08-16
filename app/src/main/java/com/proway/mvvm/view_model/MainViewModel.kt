package com.proway.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.mvvm.model.Product
import com.proway.mvvm.repository.ProductRepository

class MainViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val repository = ProductRepository()

    fun fetchProdutos() {
        repository.getProducts { list, error ->
            list?.apply {
                _products.value = this
            }
            error?.apply {
                _error.value = this
            }

        }
    }

}