package com.example.cookit.category

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.cookit.api.RetrofitInstance
import com.example.cookit.filter.MealApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun MailCategoryApp(modifier: Modifier = Modifier) {

    var categories by remember {
        mutableStateOf<List<CategoriesItem?>>(emptyList())
    }

    var selectedCategory by remember { mutableStateOf("Beef") }


    RetrofitInstance.api.getCategory().enqueue(object : Callback<CategoriesResponse> {
        override fun onResponse(
            call: Call<CategoriesResponse?>,
            response: Response<CategoriesResponse?>
        ) {
            categories = response.body()?.categories ?: emptyList()

        }

        override fun onFailure(call: Call<CategoriesResponse?>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }


    )

    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        MealsLayout(
            item = categories,
            selected = selectedCategory,
            onSelectedClick = { selectedCategory = it })
        MealApp(category = selectedCategory)
    }
}


@Composable
fun MealsLayout(
    modifier: Modifier = Modifier,
    item: List<CategoriesItem?>,
    selected: String?,
    onSelectedClick: (String) -> Unit
) {
    LazyRow {


        items(item) { category ->
            MealItem(categoriesItem = category!!, selected = selected, onClick = {
                onSelectedClick(category.strCategory ?: "Beef")
            })
        }

    }
}

@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    categoriesItem: CategoriesItem,
    selected: String?,
    onClick: () -> Unit
) {
    val rainbowGradient = Brush.sweepGradient(
        colors = listOf(
            Color(0xFFFFD54F), // Yellow
            Color(0xFF81C784), // Green
            Color(0xFF4FC3F7), // Cyan/Blue
            Color(0xFFBA68C8), // Purple
            Color(0xFFE57373), // Pink/Red
            Color(0xFFFFB74D), // Orange
            Color(0xFFFFD54F)  // Back to Yellow to close the sweep cleanly
        )
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onClick()
            },
    ) {
        Spacer(Modifier.padding(top = 60.dp))

        AsyncImage(
            model = categoriesItem.strCategoryThumb,
            contentDescription = categoriesItem.strCategoryDescription,
            modifier = Modifier
                .size(96.dp)
                .border(width = 3.dp, brush = rainbowGradient, shape = CircleShape)
                .padding(6.dp)
                .clip(CircleShape),

            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = categoriesItem.strCategory ?: "UNKNOW",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            color = Color.Black

        )


    }
}

