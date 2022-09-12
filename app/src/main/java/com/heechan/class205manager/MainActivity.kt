package com.heechan.class205manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.heechan.class205manager.databinding.ActivityMainBinding
import com.heechan.class205manager.model.Meal
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var binding: ActivityMainBinding

    val toDayDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(MEAL_API_BASE_URL)
            .build()

        val retrofitService = retrofit.create(MealService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val date = LocalDate.now()
            val mealApiResult = retrofitService.getMeal()
            var mealList : List<Meal> = mealApiResult.mealList

            mealList = mealList.slice(date.dayOfMonth - 1 until mealList.size)

            withContext(Dispatchers.Main) {
                val adapter = MealListAdapter(mealList, date.year, date.month.value)
                binding.listMainMealItem.adapter = adapter

                val dividerItemDecoration = DividerItemDecoration(
                    applicationContext, LinearLayoutManager(this@MainActivity).orientation
                )
                binding.listMainMealItem.addItemDecoration(dividerItemDecoration)
            }
        }
    }

    companion object {
        const val MEAL_API_BASE_URL = "https://schoolmenukr.ml"
    }
}