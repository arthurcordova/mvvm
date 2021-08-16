package com.proway.mvvm.repository

import com.proway.mvvm.model.Product
import com.proway.mvvm.services.RetrofitBuilder
import retrofit2.Callback

class ProductRepository {

    private val service = RetrofitBuilder.getProductServices()

    fun getProducts(callBack: Callback<List<Product>>) {
        val call = service.getProducts()
        call.enqueue(callBack)
    }


}