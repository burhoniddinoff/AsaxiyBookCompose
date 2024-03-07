package com.example.asaxiybookcompose.domain

import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    fun loginUser(password: String, gmail: String): Flow<Result<String>>

    fun registerUser(name: String, gmail: String, password: String): Flow<Result<Unit>>

}