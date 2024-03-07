@file:OptIn(ExperimentalFoundationApi::class)

package com.example.asaxiybookcompose.presentation.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.presentation.screen.audio.AudioContent
import com.example.asaxiybookcompose.presentation.screen.book.BookContent
import com.example.asaxiybookcompose.presentation.screen.library.LibraryContent
import com.example.asaxiybookcompose.presentation.screen.profile.ProfileContent
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class MainScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {

        AsaxiyBookComposeTheme {

            val pageState: PagerState = rememberPagerState(pageCount = { 4 })
            var pos by remember { mutableIntStateOf(0) }

            ScreenContent(pageState, onCLick = {
                pos = it
            })

            LaunchedEffect(key1 = pos) {
                pageState.scrollToPage(pos)
            }

        }

    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenContent(pageState: PagerState, onCLick: (Int) -> Unit) {

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            val items = listOf(
                NavigationItem.Home,
                NavigationItem.Favorites,
                NavigationItem.Audio,
                NavigationItem.Profile
            )

            var selectedItem by remember { mutableIntStateOf(0) }

            HorizontalPager(
                state = pageState,
                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                when (it) {
                    0 -> {
                        BookContent()
                    }

                    1 -> {
                        LibraryContent()
                    }

                    2 -> {
                        AudioContent()
                    }

                    3 -> {
                        ProfileContent()
                    }
                }

            }

            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                backgroundColor = Color.White
            ) {

                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            onCLick(index)
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontSize = 10.sp,
                                color = if (selectedItem == index) Color(0xFF008dff) else Color(0xFF94a3b8)
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = if (selectedItem == index) Color(0xFF008dff) else Color(0xFF94a3b8)
                            )
                        })
                }
            }
        }
    }


}

enum class NavigationItem(val icon: Int, val title: String) {
    Home(R.drawable.ic_menu_book, "Kitoblarim"),
    Favorites(R.drawable.ic_menu_library, "Kutubxona"),
    Audio(R.drawable.ic_menu_audio, "Audio kitoblar"),
    Profile(R.drawable.ic_menu_profile, "Profil")
}

@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {
        ScreenContent(pageState = rememberPagerState(pageCount = { 1 }), {})
    }
}