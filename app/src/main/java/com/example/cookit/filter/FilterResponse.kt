package com.example.cookit.filter

import com.google.gson.annotations.SerializedName

data class FilterResponse(

    @field:SerializedName("meals")
    val meals: List<MealsItem?>? = null
)