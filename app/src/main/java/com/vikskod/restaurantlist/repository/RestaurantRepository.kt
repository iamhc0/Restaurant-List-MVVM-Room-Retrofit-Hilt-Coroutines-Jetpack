package com.vikskod.restaurantlist.repository

import android.util.Log
import com.vikskod.restaurantlist.data.local.RestaurantDao
import com.vikskod.restaurantlist.data.model.FoodHitX
import com.vikskod.restaurantlist.data.remote.RestaurantRemoteDataSource
import com.vikskod.restaurantlist.utils.performGetOperation
import javax.inject.Inject


/**
 * Created by Hamza Chaudhary
 * Sr. Software Engineer Android
 * Created on 28 July,2022 09:27
 * Copyright (c) All rights reserved.
 */

class RestaurantRepository @Inject constructor(
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val restaurantDao: RestaurantDao
) {

    val TAG = RestaurantRepository::class.java.name
    fun getAllRestaurant() = performGetOperation(
        databaseQuery = { restaurantDao.getAllRestaurant() },
        networkCall = {
            remoteDataSource.getRestaurant(
            )
        },
        saveCallResult = {
            // Unwanted JsonObject is added on api response
            Log.d(TAG, "getAllRestaurant- Api Response: $it")
            val finalData = ArrayList<FoodHitX>()
            for (item in it.hits) {
                finalData.add(FoodHitX(item._id,item._index,item._type,item._score,item.fields))
            }
            restaurantDao.insertAll(finalData)
        }
    )

    fun getFavouriteRestaurant() = restaurantDao.getFavouriteRestaurant()

    suspend fun setFavouriteRestaurant(restaurant: FoodHitX) = restaurantDao.update(restaurant)
}