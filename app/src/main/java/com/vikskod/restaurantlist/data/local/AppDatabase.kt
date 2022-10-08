package com.vikskod.restaurantlist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vikskod.restaurantlist.data.model.FoodHitX
import com.vikskod.restaurantlist.utils.Config.DB_VERSION

@Database(entities = [FoodHitX::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}