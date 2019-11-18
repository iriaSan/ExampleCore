package com.iriasan.examplecore.data.api

import androidx.lifecycle.LiveData
import com.cexmobility.corekotlin.data.api.ApiResponse
import com.iriasan.examplecore.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET(".")
    fun getListOfUsers(
        @Query("results") results: Int?
    ): LiveData<ApiResponse<UserResponse>>
}
