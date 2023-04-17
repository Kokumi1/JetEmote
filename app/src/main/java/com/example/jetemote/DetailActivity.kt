package com.example.jetemote

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.jetemote.model.Emote
import com.example.jetemote.service.SharedTalker
import com.example.jetemote.ui.theme.JetEmoteTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetEmoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    LazyColumn(
                        Modifier.fillMaxSize()
                    ){
                        items(
                            items = getDetail(context),
                            itemContent = {
                                EmotesListItem(emote =  it)
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun EmotesListItem(emote: Emote){
        val context = LocalContext.current
        Row {
            Column(modifier = Modifier
                .clickable { chosen(emote, context) }){
                Image(painter = painterResource(id = emote.imageId),
                    contentDescription = emote.description,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = emote.color)
                        .clip(CircleShape))
            }
        }
    }

    private fun getDetail(pContext: Context): List<Emote>{
        return SharedTalker().getData(pContext)
    }
}