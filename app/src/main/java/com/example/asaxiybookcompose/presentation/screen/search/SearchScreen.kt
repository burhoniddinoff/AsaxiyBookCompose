package com.example.asaxiybookcompose.presentation.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class SearchScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel = getViewModel<SearchVM>()
        val searchList by viewModel.resultBooks.collectAsState(initial = null)

        searchList?.let {
            SearchContent(it, viewModel::evenDispatcher)
        }


    }
}

@Composable
fun SearchContent(data: List<BookUIData>, eventListener: (SearchIntent) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172B))

    ) {

        var search by rememberSaveable { mutableStateOf("") }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .padding(10.dp)
                    .size(56.dp)
                    .padding(10.dp)
                    .clickable {
                        eventListener(SearchIntent.BackScreen)
                    }
            )

            TextField(
                value = search,
                onValueChange = {
                    search = it
                    eventListener(SearchIntent.ResultSearchBook(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp, top = 10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                placeholder = { Text("Kitoblarni qidirish", fontWeight = FontWeight(100)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                maxLines = 1,
                shape = RoundedCornerShape(12.dp),
                textStyle = TextStyle(color = Color.Black)
            )
            "search : TextField = $search".myLog()

        }

        LazyColumn {
            data.forEach {
                item(it) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 15.dp, vertical = 10.dp)
                            .clickable {
                                eventListener(SearchIntent.NextScreen(it))
                            }
                    ) {

                        ElevatedCard(
                            modifier = Modifier
                                .width(160.dp)
                                .fillMaxHeight()
                                .clip(shape = RoundedCornerShape(10.dp)),
                        ) {
                            AsyncImage(
                                model = it.coverImage,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                error = painterResource(id = R.drawable.book),
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp, top = 20.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = it.name,
                                color = Color.White,
                                fontSize = 20.sp
                            )

                            Text(
                                text = it.author,
                                color = Color(0xFF81FFFFFF),
                                fontSize = 17.sp,
                                modifier = Modifier.padding(top = 20.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .padding(top = 40.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFF25FF5E00))
                                    .align(Alignment.End)

                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_menu_book),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(8.dp),
                                    tint = Color(0xFFDA6E34)
                                )
                            }
                        }

                    }

                }
            }
        }

    }

}

@Preview
@Composable
private fun PrevContent() {

    AsaxiyBookComposeTheme {

    }

}