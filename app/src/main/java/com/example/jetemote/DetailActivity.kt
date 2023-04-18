package com.example.jetemote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                    Scaffold(
                        topBar = {
                            TopAppBar{
                                Text(text = "JetEmote")

                                Button(onClick = {
                                    context.startActivity(Intent(context,MainActivity::class.java))
                                },
                                    contentPadding = PaddingValues(10.dp)
                                ) {
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Go to DetailActivity")
                                }
                            }
                        }
                    ) {
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
    }

    @Composable
    fun EmotesListItem(emote: Emote){
        Row {
            Column(){
                Image(painter = painterResource(id = emote.imageId),
                    contentDescription = emote.description,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = emote.color)
                        .clip(CircleShape))
                
                Text(text = emote.comment)
            }
        }
    }

    private fun getDetail(pContext: Context): List<Emote>{
        return SharedTalker().getData(pContext)
    }
}