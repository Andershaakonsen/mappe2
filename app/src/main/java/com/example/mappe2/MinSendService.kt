package com.example.mappe2

import android.Manifest
import android.app.Activity
import android.app.Service
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.preference.PreferenceManager
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import com.example.mappe2.data.AppDatabase
import com.example.mappe2.model.Avtale
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import kotlin.math.log


class MinSendService():  Service(){


    val SHARED_PREFS = "sharedPrefs"


    @Nullable
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


   


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(applicationContext, "fra SendService", Toast.LENGTH_SHORT).show()

        saveSharedPrefs("Husk din avtale i dag!")

        val sharedPreferences: SharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        //val standardMelding: String? = sharedPreferences.getString("MELDING_KEY", null)




        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        //Starts worker thread
        executor.execute{

            //Converts date to string
            val currentDateStr = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
            val avtaleFormat = SimpleDateFormat("dd-MM-yyyy")


            val db = AppDatabase.getDatabase(this)
            val avtaler = db.avtaleDao().readAllDataToday()



            val dagensAvtaler = ArrayList<Avtale>()
            avtaler.forEach {
                val str = avtaleFormat.format(it.dato)
                if(str == currentDateStr){
                    dagensAvtaler.add(it)
                }

            }

            Log.d(TAG, "Fra sendservice --> dagensAvtaler: ${dagensAvtaler} ")
            //Sends message out for each avtale today
            dagensAvtaler.forEach{
                val phoneNumber = db.avtaleDao().getPhoneNumber(it.navn)
                sendSMS(it, phoneNumber)
            }



            handler.post {

            }
        }


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Toast.makeText(this, "sendservice stoppet", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }



    fun sendSMS(avtale: Avtale, telefonnr: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val standardMelding: String = sharedPreferences.getString("MELDING_KEY", null).toString()

        Log.d(TAG, "Inne i sendSMS, hentet inn avltale: ${avtale}, standarmelding: ${standardMelding}, melding: ${avtale.melding}")

        val smsManager = SmsManager.getDefault()
        val melding: String
        melding = if(avtale.melding.isEmpty()){
            standardMelding
        }else{
            avtale.melding
        }

        Log.d(TAG, "melding: ${melding} til navn: ${avtale.navn}")

        smsManager.sendTextMessage(telefonnr, null, melding, null, null)
        Log.d(TAG, "sendSMS: ${melding} til ${avtale.navn} sendt!")
    }

    fun saveSharedPrefs(melding: String){
        val sharedPreferences: SharedPreferences =  getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putString("MELDING_KEY", melding)
        }.apply()
    }

    fun loadSharedPrefs(avtale: Avtale){
        val sharedPreferences: SharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val melding: String? = sharedPreferences.getString("MELDING_KEY", null)
        val tidspunkt: String? = sharedPreferences.getString("TIDSPUNKT_KEY", null)


    }


    }




