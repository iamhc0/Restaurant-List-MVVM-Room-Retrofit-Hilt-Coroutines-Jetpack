package com.vikskod.restaurantlist.di

import com.vikskod.restaurantlist.data.local.RestaurantDao
import com.vikskod.restaurantlist.data.remote.RestaurantRemoteDataSource
import com.vikskod.restaurantlist.repository.RestaurantRepository
import com.vikskod.restaurantlist.ui.adapter.RestaurantAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.ArrayList
import javax.inject.Singleton


/**
 * Created by Hamza Chaudhary
 * Sr. Software Engineer Android
 * Created on 28 July,2022 09:27
 * Copyright (c) All rights reserved.
 */


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RestaurantRemoteDataSource,
        restaurantDao: RestaurantDao
    ) = RestaurantRepository(remoteDataSource, restaurantDao)

    @Provides
    fun provideRestaurantAdapter(): RestaurantAdapter = RestaurantAdapter()
}