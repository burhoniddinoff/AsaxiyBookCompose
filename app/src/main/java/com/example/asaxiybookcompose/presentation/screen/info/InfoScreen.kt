package com.example.asaxiybookcompose.presentation.screen.info

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.myLog
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach

class InfoScreen(private val product: BookUIData) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<InfoViewModelImpl>()

        viewModel.onEventDispatcherLibrary(InfoViewModel.OpenBook(product))

        val book by viewModel.book.collectAsState(initial = null)

        book?.let {
            "kitob null emas".myLog()

            val pdfState = rememberVerticalPdfReaderState(
                resource = ResourceType.Local(Uri.fromFile(it)),
                isZoomEnable = true
            )

            VerticalPDFReader(
                state = pdfState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray)
            )

            LaunchedEffect(key1 = pdfState.error) {
                pdfState.error?.let {
                    // Show error
                }
            }
        }
    }
}