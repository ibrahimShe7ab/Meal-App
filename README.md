




Cookit 🍽️

Cookit is an Android app built with Kotlin and Jetpack Compose that uses the TheMealDB API to display meal categories and meals by category.

Features

Display food categories in a horizontal LazyRow.

Select a category, with Beef as the default.

Fetch meals using Retrofit.

Display meals in a two-column LazyVerticalGrid.

Load images with Coil 3.

Show meal names and images in simple cards.

Technologies

Kotlin

Jetpack Compose

Material 3

Retrofit

Coil 3

TheMealDB API

Project Structure

com.example.cookit
├── api
├── category
│   ├── MailCategoryApp
│   ├── MealsLayout
│   └── MealItem
└── filter
    ├── MealApp
    ├── MealLayout
    └── MealItem

API Methods

RetrofitInstance.api.getCategory()
RetrofitInstance.api.getByCategory(category)

App Flow

Load meal categories.

Display categories in a LazyRow.

Select a category.

Fetch meals for the selected category.

Display meals in a two-column grid.



https://github.com/user-attachments/assets/05703816-5259-4ea6-b089-577235c1c973

