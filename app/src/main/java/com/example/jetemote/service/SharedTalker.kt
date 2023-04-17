package com.example.jetemote.service

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.jetemote.R
import com.example.jetemote.model.Emote
import java.util.Calendar

class SharedTalker {
    private val today = Calendar.getInstance().time
    fun getData(pContext: Context): List<Emote>{
        val data = ArrayList<Emote>()
        val sharedPreferences = pContext.getSharedPreferences(
            pContext.getString(R.string.shared_key),Context.MODE_PRIVATE)

        while (data.size<7){
            when(val imageId = sharedPreferences.getInt("${today}id",R.drawable.neutral)){
                R.drawable.neutral-> data.add(Emote(
                    imageId,"neutral", Color.Gray,
                    sharedPreferences.getString("${today}comment","")!!))

                R.drawable.smile-> data.add(Emote(
                    imageId,"happy", Color.Yellow,
                    sharedPreferences.getString("${today}comment","")!!))

                R.drawable.sad-> data.add(Emote(
                    imageId,"sad",Color.Red,
                    sharedPreferences.getString("${today}comment","")!!))

            }
        }

        return data
    }

    fun addData(pEmote: Emote, pContext: Context){

        val sharedPreferences = pContext.getSharedPreferences(
            pContext.getString(R.string.shared_key),Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putInt("${today}id",pEmote.imageId)
        editor.putString("${today}comment",pEmote.comment)
            .apply()
    }
}