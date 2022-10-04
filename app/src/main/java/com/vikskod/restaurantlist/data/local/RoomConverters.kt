package com.vikskod.restaurantlist.data.local

import android.location.Location
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vikskod.restaurantlist.data.model.Fields

class RoomConverters {

    // for converting the json object or String into Pojo or DTO class
    @TypeConverter
    fun toLocation(value: String?): Fields {
        return Gson().fromJson(
            value,
            object : TypeToken<Fields?>() {}.type
        )
    }

    @TypeConverter
    fun fromLocation(data: Fields?): String {
        return Gson().toJson(data)

    }
}