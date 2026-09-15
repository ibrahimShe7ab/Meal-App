package com.example.cookit.api

import com.example.cookit.category.CategoriesResponse
import com.example.cookit.filter.FilterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallable {

    @GET("1/categories.php")
    fun getCategory(): Call<CategoriesResponse>


    @GET("1/filter.php")
    fun getByCategory(

        @Query("c") category: String
    ): Call<FilterResponse>
}



