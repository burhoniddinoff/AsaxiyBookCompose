package com.example.asaxiybookcompose.presentation.screen.audio_item

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

class AudioItemScreen(val data: BookUIData) : Screen {
    private var mediaPlayer: MediaPlayer? = null

    @Composable
    override fun Content() {

        val viewModel = getViewModel<AudioItemVM>()


        AudioItemContent(data, viewModel::onEventDispatcher, mediaPlayer)

        viewModel.onEventDispatcher(AudioItemIntent.OpenAudio(data))

        val audio by viewModel.audio.collectAsState(initial = null)

        mediaPlayer?.stop()

        audio?.let {
            "audio null emas".myLog()

            val context = LocalContext.current
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(
                    context, Uri.fromFile(it)
                )

                mediaPlayer?.let {
                    if (!mediaPlayer!!.isPlaying) {
                        mediaPlayer!!.start()
                    }
                }
            }
//            else mediaPlayer?.stop()
        }
    }

}

@Composable
fun AudioItemContent(data: BookUIData, eventListener: (AudioItemIntent) -> Unit, mediaPlayer: MediaPlayer?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172B))
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            modifier = Modifier
                .size(56.dp)
                .padding(16.dp)
                .clickable {
                    eventListener(AudioItemIntent.OnClickBack)
                }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.img_umar_ibn_xattob),
//                contentDescription = "Umar",
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )

            AsyncImage(
                model = data.coverImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.book),
            )
        }

        Text(
            text = data.name,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        )

        Text(
            text = data.author,
            color = Color(0xFF747b81),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
        )

        Slider(
            value = 0.1f,
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp),
            colors = SliderDefaults.colors(
                activeTrackColor = Color.Black,
                thumbColor = Color.White
            ),
            enabled = true,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {

            Text(
                text = "00:00",
                color = Color(0xFF747b81)
            )

            Text(
                text = "00:00",
                color = Color(0xFF747b81),
                modifier = Modifier.align(Alignment.BottomEnd)
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 60.dp,
                    start = 60.dp,
                    end = 60.dp
                )
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_prev),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterStart)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.Center)
                    .clickable {
                        mediaPlayer?.let {
                            if (mediaPlayer.isPlaying) {
                                mediaPlayer.stop()
                            }
                            else {
                                mediaPlayer.start()
                            }
                        }
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_next_2),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterEnd)
            )

        }

    }

}


@Preview
@Composable
private fun AudioItemPrev() {
    AsaxiyBookComposeTheme {

    }
}