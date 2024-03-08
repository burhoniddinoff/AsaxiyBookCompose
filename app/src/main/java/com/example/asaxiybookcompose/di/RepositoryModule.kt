package com.example.asaxiybookcompose.di

import com.example.asaxiybookcompose.domain.AppRepository
import com.example.asaxiybookcompose.domain.AudioRepository
import com.example.asaxiybookcompose.domain.BookRepository
import com.example.asaxiybookcompose.domain.Impl.BookRepositoryImpl
import com.example.asaxiybookcompose.domain.Impl.AppRepositoryImpl
import com.example.asaxiybookcompose.domain.Impl.AudioRepositoryImpl
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

    @[Binds Singleton]
    fun bindBook(impl: BookRepositoryImpl) : BookRepository


    @[Binds Singleton]
    fun bindAudio(impl: AudioRepositoryImpl) : AudioRepository
}