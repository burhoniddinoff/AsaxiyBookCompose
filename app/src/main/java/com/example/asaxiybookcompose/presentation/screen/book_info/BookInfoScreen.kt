package com.example.asaxiybookcompose.presentation.screen.book_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.data.source.MySharedPreference

class BookInfoScreen(val data: BookUIData) : Screen {
    @Composable
    override fun Content() {
        val viewModel: BookDetailViewModel = getViewModel<BookDetailViewModelImpl>()
        val pos = viewModel.pos
        BookDetailsContent(data = data, pos) {
            viewModel.onD(it)
        }
    }
}

@Composable
fun BookDetailsContent(data: BookUIData, pos: State<Int>, onD: (IntentInfo) -> Unit) {
    val scrollState = rememberScrollState()
    val prev = MySharedPreference
    Column(
        modifier = Modifier
            .padding(pos.value.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Image(
            painter = painterResource(
                R.drawable.ic_back
            ),
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .fillMaxWidth()
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(86.dp - 36.dp))

        Card(
            elevation = 4.dp,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.size(width = 200.dp, height = 300.dp)
        ) {
            AsyncImage(
                model = data.coverImage,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.book),
                error = painterResource(id = R.drawable.ic_log_out),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = data.name,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = data.author,
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (prev.getBookLink(bookId = data.docID) == "") {
            Button(
                onClick = {
                    onD.invoke(IntentInfo.Download(data))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Yuklash", fontSize = 18.sp)
            }
        } else {
            Button(
                onClick = {
                    onD.invoke(IntentInfo.Read(data))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Oqish", fontSize = 18.sp)
            }
        }


        Spacer(modifier = Modifier.height(32.dp))


        Text(
            text = data.description,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )


    }
}
