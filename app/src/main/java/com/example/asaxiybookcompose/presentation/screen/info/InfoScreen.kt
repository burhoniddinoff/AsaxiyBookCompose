package com.example.asaxiybookcompose.presentation.screen.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.data.data.BookUIData
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

class InfoScreen(private val product: BookUIData) : Screen {
    @Composable
    override fun Content() {
        val pdfState = rememberVerticalPdfReaderState(
            resource = ResourceType.Remote(product.bookUrl),
            isZoomEnable = true
        )

        VerticalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        )
    }
}