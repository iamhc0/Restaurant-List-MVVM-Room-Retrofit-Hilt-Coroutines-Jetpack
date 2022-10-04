package com.vikskod.restaurantlist.data.remote

import com.vikskod.restaurantlist.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Hamza Chaudhary
 * Sr. Software Engineer Android
 * Created on 28 July,2022 09:27
 * Copyright (c) All rights reserved.
 */

interface ApiService {
    @GET("search/{item_name}")
    suspend fun getRestaurantData(
        @Header("X-RapidAPI-Key") header_key: String,
        @Header("X-RapidAPI-Host") header_host: String ,
        @Path(value = "item_name", encoded = true) item_name: String,
        @Query("fields") field: String



    ): Response<ApiResponse>
}