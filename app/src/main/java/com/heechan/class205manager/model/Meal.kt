package com.heechan.class205manager.model

import androidx.activity.result.contract.ActivityResultContracts
import com.squareup.moshi.Json

data class Meal (
    val date : Int,
    @Json(name = "lunch")
    val menu : List<String>
)

data class MealApiResult (
    val server_message : List<String>,
    @Json(name = "menu")
    val mealList : List<Meal>,
)