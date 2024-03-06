package com.example.asaxiybookcompose.di

import com.example.asaxiybookcompose.navigation.AppNavigationHandler
import com.example.asaxiybookcompose.navigation.AppNavigator
import com.example.asaxiybookcompose.navigation.AppNavigatorDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindNavigation(dispatcher: AppNavigatorDispatcher): AppNavigator

    @Binds
    fun bindHandler(dispatcher: AppNavigatorDispatcher): AppNavigationHandler

}