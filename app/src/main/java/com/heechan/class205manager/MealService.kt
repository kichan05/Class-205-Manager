package com.heechan.class205manager

import com.heechan.class205manager.model.MealApiResult
import com.heechan.class205manager.utill.SunrinData
import retrofit2.http.GET

interface MealService {
    @GET("api/middle/${SunrinData.API_SCHOOL_CODE}")
    suspend fun getMeal() : MealApiResult
}