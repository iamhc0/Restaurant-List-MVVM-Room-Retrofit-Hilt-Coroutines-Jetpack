package com.vikskod.restaurantlist.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vikskod.restaurantlist.data.model.FoodHitX

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM tbl_restaurant")
    fun getAllRestaurant(): LiveData<List<FoodHitX>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurant: List<FoodHitX>)

    @Update
    suspend fun update(restaurant: FoodHitX)

    @Delete
    suspend fun delete(restaurant: FoodHitX)

    @Query("SELECT * FROM tbl_restaurant WHERE _score >= :score")
    fun getFavouriteRestaurant(score: Int = 20): LiveData<List<FoodHitX>>
}