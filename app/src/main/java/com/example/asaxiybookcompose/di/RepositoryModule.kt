package com.example.asaxiybookcompose.di

import com.example.asaxiybookcompose.domain.AppRepository
import com.example.asaxiybookcompose.domain.Impl.AppRepositoryImpl
import com.example.asaxiybookcompose.domain.Impl.RegisterRepositoryImpl
import com.example.asaxiybookcompose.domain.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(impl: AppRepositoryImpl): AppRepository

    @[Binds Singleton]
    fun bindLogin(impl: RegisterRepositoryImpl):RegisterRepository
}