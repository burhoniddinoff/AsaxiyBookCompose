package com.example.asaxiybookcompose.presentation.screen.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme


data class LibraryData(
    val bookImage: Int,
    val bookName: String,
    val bookAuthor: String,
)

data class CategoryData(
    val name: String,
    val ls: List<LibraryData>,
)

val arrayList = ArrayList<LibraryData>().apply {
    add(LibraryData(R.drawable.book, "O'tkan kunlar", "Abdulla Qodiriy"))
    add(LibraryData(R.drawable.book, "O'tkan kunlar", "Abdulla Qodiriy"))
    add(LibraryData(R.drawable.book, "O'tkan kunlar", "Abdulla Qodiriy"))
    add(LibraryData(R.drawable.book, "O'tkan kunlar", "Abdulla Qodiriy"))
    add(LibraryData(R.drawable.book, "O'tkan kunlar", "Abdulla Qodiriy"))
    add(LibraryData(R.drawable.book, "O'tkan kunlar", "Abdulla Qodiriy"))
}

val ls = ArrayList<CategoryData>().apply {
    add(CategoryData("Books1", arrayList))
    add(CategoryData("Books2", arrayList))
    add(CategoryData("Books3", arrayList))
}


class LibraryScreen : Screen {
    @Composable
    override fun Content() {
        LibraryContent()

    }
}

@Composable
fun LibraryContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF0F172B))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
        ) {
            Text(
                text = "Kutubxona",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                modifier = Modifier
                    .padding(start = 14.dp, top = 14.dp)
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 14.dp, top = 14.dp)
                    .size(36.dp)
                    .padding(6.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        LazyColumn {

            ls.forEach { data ->

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 5.dp),
                    ) {

                        Text(
                            text = data.name,
                            color = Color.White,
                            fontSize = 23.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                        )

                        Text(
                            text = "Hammasi",
                            color = Color(0xFF008dff),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                        )


                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentHeight()
                            .padding(bottom = 20.dp),
                    ) {
                        data.ls.forEach { product ->
                            item {
                                ItemLibrary(product = product)
                            }
                        }
                    }
                }

            }

        }

    }

}

@Composable
fun ItemLibrary(product: LibraryData) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172B))
            .padding(6.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .width(160.dp)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(10.dp)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.book),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        Text(text = product.bookName, modifier = Modifier.padding(top = 10.dp), fontSize = 18.sp, color = Color.White)
        Text(text = product.bookAuthor, fontSize = 14.sp, color = Color(0xFF59688F))
    }


}


@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {
        ItemLibrary(arrayList[0])
    }
}