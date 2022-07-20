package com.auro.openactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

public class OpenInclusion
{
    companion object
    {
        public fun opensplash(context: Context,myclass:AppCompatActivity)
        {
            context.startActivity(Intent(context,myclass::class.java))
        }
    }
}