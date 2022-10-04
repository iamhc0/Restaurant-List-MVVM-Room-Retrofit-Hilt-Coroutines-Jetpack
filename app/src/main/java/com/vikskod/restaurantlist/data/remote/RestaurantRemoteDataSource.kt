package com.vikskod.restaurantlist.data.remote

import com.vikskod.restaurantlist.BuildConfig
import com.vikskod.restaurantlist.utils.Config.FIELD_VALUE
import com.vikskod.restaurantlist.utils.Config.ITEM_NAME
import javax.inject.Inject

class RestaurantRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    BaseDataSource() {

    suspend fun getRestaurant(
        header_key: String = BuildConfig.X_RapidAPI_Key,
        header_host: String = BuildConfig.X_RapidAPI_Host,
        item_name: String = ITEM_NAME,
        field: String = FIELD_VALUE

    ) = getResult {
        apiService.getRestaurantData(
            header_key, header_host, item_name,field,
        )
    }

}