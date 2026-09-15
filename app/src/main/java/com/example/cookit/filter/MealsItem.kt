package com.example.cookit.filter

import com.google.gson.annotations.SerializedName

data class MealsItem(

    @field:SerializedName("strCountry")
    val strCountry: String? = null,

    @field:SerializedName("strMealThumb")
    val strMealThumb: String? = null,

    @field:SerializedName("strArea")
    val strArea: Any? = null,

    @field:SerializedName("idMeal")
    val idMeal: String? = null,

    @field:SerializedName("strMeal")
    val strMeal: String? = null
)