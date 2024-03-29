package com.example.asaxiybookcompose.presentation.screen.audio

import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme
import com.sudo_pacman.asaxiybooks.data.model.CategoryByBooksData

class AudioScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<AudioVM>()
        viewModel.onEventDispatcherLibrary(AudioIntent.GetAllCategoryList)
        val categoryList by viewModel.loadCategoryBookList.collectAsState(initial = null)

        val message by viewModel.errorMessage.collectAsState(initial = null)
        if (message != null) {
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        }

        categoryList?.let {
            LibraryContent(it, viewModel::onEventDispatcherLibrary)
        }
    }
}

@Composable
fun LibraryContent(data: List<CategoryByBooksData>, eventListener: (AudioIntent) -> Unit) {

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
                text = "Audio books",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                modifier = Modifier
                    .padding(start = 14.dp, top = 14.dp)
                    .weight(1f)
            )

//            Image(
//                painter = painterResource(id = R.drawable.ic_search),
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(end = 14.dp, top = 14.dp)
//                    .size(36.dp)
//                    .padding(6.dp)
//                    .align(Alignment.CenterVertically)
//            )
        }

        LazyColumn {

            items(data) {
                ItemCategory(it, eventListener)
            }

        }

    }

}

@Composable
fun ItemCategory(data: CategoryByBooksData, eventListener: (AudioIntent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 5.dp),
    ) {

        Text(
            text = data.categoryName,
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
        items(data.books) {
            ItemLibrary(product = it, eventListener)
        }
    }
}

@Composable
fun ItemLibrary(product: BookUIData, eventListener: (AudioIntent) -> Unit) {


    Column(
        modifier = Modifier
            .clickable {
                eventListener(AudioIntent.ButtonClick(product))
            }
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

//            Image(
//                painter = painterResource(id = R.drawable.book),
//                contentDescription = null,
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop,
//            )

            AsyncImage(
                model = product.coverImage,
                contentDescription = null,
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.book),
                error = painterResource(id = R.drawable.ic_log_out),
            )

        }

        Text(
            text = product.name, modifier = Modifier
                .padding(top = 10.dp)
                .width(100.dp), fontSize = 18.sp, color = Color.White, maxLines = 2
        )
        Text(text = product.author, fontSize = 14.sp, color = Color(0xFF59688F))

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(24.dp)
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Color(0xFF25FF5E00))
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_menu_audio),
                contentDescription = null,
                tint = Color(0xFFDA6E34),
                modifier = Modifier
                    .padding(start = 4.dp, end = 10.dp)
                    .size(20.dp)
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "Audio kitob",
                color = Color(0xFFDA6E34),
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 10.dp)
            )

        }


    }


}

@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {

    }
}
