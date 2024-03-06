package com.example.asaxiybookcompose.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigatorDispatcher @Inject constructor() : AppNavigator, AppNavigationHandler {
    override val backStage = MutableSharedFlow<AppNavigatorArgs>()

    private suspend fun navigator(arg: AppNavigatorArgs) {
        backStage.emit(arg)
    }

    override suspend fun back() = navigator {
        pop()
    }

    override suspend fun navigate(screen: AndroidScreen) = navigator {
        push(screen)
    }

    override suspend fun replace(screen: AndroidScreen) = navigator {
        replace(screen)
    }
}