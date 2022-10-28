package com.example.mappe2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MinBroadCastReceiver: BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val i: Intent = Intent(context, MinPeriodisk::class.java)
        Toast.makeText(context.applicationContext, "Fra broadcast", Toast.LENGTH_SHORT).show()

        context.startService(i)
    }
}