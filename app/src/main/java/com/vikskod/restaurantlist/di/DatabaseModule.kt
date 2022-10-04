package com.vikskod.restaurantlist.di

import android.content.Context
import androidx.room.Room
import com.vikskod.restaurantlist.data.local.AppDatabase
import com.vikskod.restaurantlist.data.local.RestaurantDao
import com.vikskod.restaurantlist.utils.Config.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Hamza Chaudhary
 * Sr. Software Engineer Android
 * Created on 28 July,2022 09:27
 * Copyright (c) All rights reserved.
 */


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase): RestaurantDao {
        return appDatabase.restaurantDao()
    }

}