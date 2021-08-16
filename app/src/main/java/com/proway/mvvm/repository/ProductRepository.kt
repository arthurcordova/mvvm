package com.proway.mvvm.repository

import com.proway.mvvm.model.Product
import com.proway.mvvm.services.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    private val service = RetrofitBuilder.getProductServices()

    fun getProducts(callBack: (List<Product>?, String?) -> Unit) {

        val call = service.getProducts()
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                response.body()?.let {
                    callBack(it, null)
                    callBack(null, "Sucesso!!!!!!")
                }
                if (response.code() != 200) {
                    callBack(null, "Algum erro desconhecido")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callBack(null, t.localizedMessage)
            }
        })
    }


}