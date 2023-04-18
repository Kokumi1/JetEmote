package com.example.jetemote.service

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.jetemote.R
import com.example.jetemote.model.Emote
import java.util.*
import kotlin.collections.ArrayList

class SharedTalker {
    private val today = Calendar.getInstance()
    fun getData(pContext: Context): List<Emote>{
        val data = ArrayList<Emote>()
        val sharedPreferences = pContext.getSharedPreferences(
            pContext.getString(R.string.shared_key),Context.MODE_PRIVATE)

        var seekDay = today.time
        while (data.size<7){
            when(val imageId = sharedPreferences.getInt("${seekDay}id",R.drawable.neutral)){
                R.drawable.neutral-> data.add(Emote(
                    imageId,"neutral", Color.Gray,
                    sharedPreferences.getString("${seekDay}comment","")!!))

                R.drawable.smile-> data.add(Emote(
                    imageId,"happy", Color.Yellow,
                    sharedPreferences.getString("${seekDay}comment","")!!))

                R.drawable.sad-> data.add(Emote(
                    imageId,"sad",Color.Red,
                    sharedPreferences.getString("${seekDay}comment","")!!))

            }
            seekDay = Date(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)-data.size)
        }

        return data
    }

    fun addData(pEmote: Emote, pContext: Context){

        val sharedPreferences = pContext.getSharedPreferences(
            pContext.getString(R.string.shared_key),Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putInt("${today.time}id",pEmote.imageId)
        editor.putString("${today.time}comment",pEmote.comment)
            .apply()
    }
}