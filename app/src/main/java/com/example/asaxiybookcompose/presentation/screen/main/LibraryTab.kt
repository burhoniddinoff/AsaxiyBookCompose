package com.example.asaxiybookcompose.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.presentation.screen.library.LibraryScreen

object LibraryTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Kutubxona"
            val icon = painterResource(R.drawable.ic_menu_library)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LibraryScreen().Content()
        }
    }
}