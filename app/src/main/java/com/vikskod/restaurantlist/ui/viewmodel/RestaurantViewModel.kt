package com.vikskod.restaurantlist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikskod.restaurantlist.data.model.HitX
import com.vikskod.restaurantlist.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val repository: RestaurantRepository) :
    ViewModel() {

    val getAllRestaurant = repository.getAllRestaurant()
    val getFavouriteRestaurant = repository.getFavouriteRestaurant()
    fun setFavouriteRestaurant(restaurant: HitX) {
        viewModelScope.launch { repository.setFavouriteRestaurant(restaurant) }
    }
}