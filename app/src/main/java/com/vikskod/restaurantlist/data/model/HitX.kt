package com.vikskod.restaurantlist.data.model



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "tbl_restaurant")
data class HitX(

    @PrimaryKey
    @SerializedName("_id") val _id : String,
    @SerializedName("_index") val _index : String,
    @SerializedName("_type") val _type : String,
    @SerializedName("_score") val _score : Double,
    @SerializedName("fields") val fields : Fields

) : Serializable