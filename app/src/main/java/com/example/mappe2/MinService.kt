package com.example.mappe2

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable

class MinService: Service(){
    @Nullable
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "I minService", Toast.LENGTH_LONG).show()
        return super.onStartCommand(intent, flags, startId)
    }

    }