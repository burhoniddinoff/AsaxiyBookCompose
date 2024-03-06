package com.example.asaxiybookcompose.navigation

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.Flow

interface AppNavigationHandler {
    val backStage: Flow<AppNavigatorArgs>
}

typealias AppNavigatorArgs = Navigator.() -> Unit