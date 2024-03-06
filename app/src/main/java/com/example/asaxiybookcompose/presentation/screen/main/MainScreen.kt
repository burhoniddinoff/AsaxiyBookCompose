package com.example.asaxiybookcompose.presentation.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.presentation.screen.audio.AudioScreen
import com.example.asaxiybookcompose.presentation.screen.book.BookScreen
import com.example.asaxiybookcompose.presentation.screen.library.LibraryScreen
import com.example.asaxiybookcompose.presentation.screen.profile.ProfileScreen
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

data class BottomNavigationItem(val title: String, val icon: Int)

val items = listOf(
    BottomNavigationItem(
        "Kitoblarim",
        R.drawable.ic_menu_book
    ),
    BottomNavigationItem(
        "Kutubxona",
        R.drawable.ic_menu_library
    ),
    BottomNavigationItem(
        "Audio kitoblar",
        R.drawable.ic_menu_audio
    ),
    BottomNavigationItem(
        "Profil",
        R.drawable.ic_menu_profile
    )
)

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val navController = rememberNavController()
        ScreenContent(items, navController) { screen ->
            navController.navigate(screen)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenContent(
    items: List<BottomNavigationItem>,
    navController: NavHostController,
    onItemSelected: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {

        val screens = remember(items) {
            listOf(
                "BookScreen",
                "LibraryScreen",
                "AudioScreen",
                "ProfileScreen"
            )
        }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = false,
                            onClick = { onItemSelected(screens[index]) },
                            icon = { /*TODO*/ })
                    }
                }
            }
        ) {
            NavHost(navController, startDestination = screens.first()) {
                composable("BookScreen") { BookScreen() }
                composable("LibraryScreen") { LibraryScreen() }
                composable("AudioScreen") { AudioScreen() }
                composable("ProfileScreen") { ProfileScreen() }
            }
        }

    }

}

@Preview
@Composable
private fun PreviewContent() {
    val navController = rememberNavController()
    AsaxiyBookComposeTheme {
        ScreenContent(items = items, navController = navController, {})
    }
}