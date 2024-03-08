@file:OptIn(ExperimentalFoundationApi::class)

package com.example.asaxiybookcompose.presentation.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.asaxiybookcompose.presentation.screen.tab.AudioTab
import com.example.asaxiybookcompose.presentation.screen.tab.HomeTab
import com.example.asaxiybookcompose.presentation.screen.tab.LibraryTab
import com.example.asaxiybookcompose.presentation.screen.tab.ProfileTab
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class MainScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {

        AsaxiyBookComposeTheme {

//            val pageState: PagerState = rememberPagerState(pageCount = { 4 })
//            var pos by remember { mutableIntStateOf(0) }
//
//            ScreenContent(pageState, onCLick = {
//                pos = it
//            })
//
//            LaunchedEffect(key1 = pos) {
//                pageState.scrollToPage(pos)
//            }

            ScreenContent()

        }

    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabNavigator(HomeTab) {
            Scaffold(
                content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(LibraryTab)
                        TabNavigationItem(AudioTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            )
        }
    }


}

//enum class NavigationItem(val icon: Int, val title: String) {
//    Home(R.drawable.ic_menu_book, "Kitoblarim"),
//    Favorites(R.drawable.ic_menu_library, "Kutubxona"),
//    Audio(R.drawable.ic_menu_audio, "Audio kitoblar"),
//    Profile(R.drawable.ic_menu_profile, "Profil")
//}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}
