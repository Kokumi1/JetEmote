package com.example.jetemote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetemote.model.Emote
import com.example.jetemote.ui.theme.JetEmoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetEmoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopAppBar({Text(text = "JetEmote")})
                    MainScreen(listOf(
                        Emote(R.drawable.smile,"smile", Color.Yellow),
                        Emote(R.drawable.neutral,"neutral", Color.Gray),
                        Emote(R.drawable.sad,"sad", Color.Red)
                    ), LocalContext.current)
                }
            }
        }
    }
}


@Composable
fun MainScreen(emotes: List<Emote>, pContext: Context){
    Scaffold(
        topBar = {
        TopAppBar{
            Text(text = "JetEmote")

            Button(onClick = {
                pContext.startActivity(Intent(pContext,DetailActivity::class.java))
            },
                contentPadding = PaddingValues(10.dp)
            ) {
                Icon(Icons.Filled.List, contentDescription = "Go to DetailActivity")
            }
        }
    }
    ) {
        LazyColumn(
            Modifier.fillMaxSize()
        ){
            items(
                items = emotes,
                itemContent = {
                    EmotesListItem(emote =  it)
                }
            )
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

fun chosen(emote : Emote, pContext: Context){
    Toast.makeText(pContext,"you choose ${emote.description}",Toast.LENGTH_LONG).show()
}

@Preview
@Composable
fun Preview(){
    MainScreen(listOf(
        Emote(R.drawable.smile,"happy", Color.Yellow),
        Emote(R.drawable.neutral,"neutral", Color.Gray),
        Emote(R.drawable.sad,"sad", Color.Red)
    ),LocalContext.current)
}