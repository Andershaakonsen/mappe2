package com.example.mappe2

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.Nullable
import java.util.*
import java.util.regex.Pattern

class MinPeriodisk:  Service(){
    @Nullable
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private lateinit var alarm: AlarmManager;
    private lateinit var pendingIntent: PendingIntent
    val SHARED_PREFERENCES = "sharedPreferences"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val calendarNow = Calendar.getInstance()


        //Shared Preferences
        saveSharedPrefs("09:00")
        val sharedPreferences: SharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        var klokkeslett: String = sharedPreferences.getString("TIDSPUNKT_KEY",null).toString()

        val TID: Pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
        if(!TID.matcher(klokkeslett).matches()){
            klokkeslett = "12.00"
        }

        val brukerTid: Array<Int> = timeStringToArray(klokkeslett)
        Toast.makeText(applicationContext, "Shared klokkeslett: ${klokkeslett}", Toast.LENGTH_SHORT).show()

        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, brukerTid[0])
        cal.set(Calendar.MINUTE, brukerTid[1])
        cal.set(Calendar.SECOND, 0)

        Toast.makeText(applicationContext, "calendar tid: ${cal.time} ", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MinSendService::class.java)
        pendingIntent = PendingIntent.getService(this, 0, intent, 0)
        alarm  = getSystemService(ALARM_SERVICE) as AlarmManager
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)

        //TODO sett tidspunkt den skal kjøre på!

        Toast.makeText(this, "Fra MinPeriodisk", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)


    }

    override fun onDestroy() {
        Toast.makeText(this, "Min periodisk stopped", Toast.LENGTH_SHORT).show()
        alarm.cancel(pendingIntent)
        super.onDestroy()
    }

    fun saveSharedPrefs(tidspunkt: String){
        val sharedPreferences: SharedPreferences =  getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putString("TIDSPUNKT_KEY", tidspunkt)
        }.apply()
    }


    fun timeStringToArray(tid: String): Array<Int> {
        val del: kotlin.collections.List<String> = tid.split(":")
        val nummer1 = del[0].toInt()
        val nummer2 = del[1].toInt()


        return arrayOf(nummer1, nummer2)

    }
}