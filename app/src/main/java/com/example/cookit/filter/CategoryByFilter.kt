package com.example.cookit.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.cookit.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun MealApp(modifier: Modifier = Modifier, category: String) {
    var meal by remember { mutableStateOf<List<MealsItem?>>(emptyList()) }

    RetrofitInstance.api.getByCategory(category).enqueue(object : Callback<FilterResponse> {
        override fun onResponse(call: Call<FilterResponse?>, response: Response<FilterResponse?>) {
            meal = response.body()?.meals ?: emptyList()

        }

        override fun onFailure(call: Call<FilterResponse?>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
    MealLayout(meal)
}


@Composable
fun MealLayout(mealsItem: List<MealsItem?>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(mealsItem) {
            MealItem(it!!)
        }
    }
}


@Composable
fun MealItem(
    meal: MealsItem?
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 25.dp)
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .background(
                    Color(0xFFE7E5EA)
                )
        ) {

            Text(
                text = meal?.strMeal ?: "UNKNOW",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(
                        top = 80.dp,
                        start = 8.dp,
                        end = 8.dp
                    ),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 5
            )
        }

        AsyncImage(
            model = meal?.strMealThumb,
            contentDescription = meal?.strMeal,
            modifier = Modifier
                .size(100.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            contentScale = ContentScale.Crop
        )
    }
}
