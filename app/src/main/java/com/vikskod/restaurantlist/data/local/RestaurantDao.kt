package com.vikskod.restaurantlist.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vikskod.restaurantlist.data.model.HitX

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM tbl_restaurant")
    fun getAllRestaurant(): LiveData<List<HitX>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurant: List<HitX>)

    @Update
    suspend fun update(restaurant: HitX)

    @Delete
    suspend fun delete(restaurant: HitX)

    @Query("SELECT * FROM tbl_restaurant WHERE _score >= :score")
    fun getFavouriteRestaurant(score: Int = 20): LiveData<List<HitX>>
}