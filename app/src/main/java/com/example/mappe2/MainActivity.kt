package com.example.mappe2

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mappe2.databinding.ActivityMainBinding
import com.example.mappe2.fragments.Avtaler
import com.example.mappe2.fragments.Hjem
import com.example.mappe2.fragments.Kontakter
import com.example.mappe2.viewmodel.AvtaleViewModel
import kotlinx.android.synthetic.main.fragment_hjem.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myBroadcastReceiver: MinBroadCastReceiver
    private lateinit var filter: IntentFilter

    companion object{
        public const val SHARED_PREFS: String = "sharedPrefs"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Hjem())

        //Sette rettigheter til å sende smser her
        //val mAvtaleViewModel: AvtaleViewModel = ViewModelProvider(this).get(AvtaleViewModel::class.java)
        smsPermissionRequest()



        myBroadcastReceiver = MinBroadCastReceiver()
        filter = IntentFilter("com.example.mappe2.MITTSIGNAL")
        filter.addAction("com.example.mappe2.MITTSIGNAL")
        startSmsService()






        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.hjem -> replaceFragment(Hjem())
                R.id.kontakter -> replaceFragment(Kontakter())
                R.id.avtaler -> replaceFragment(Avtaler())
                else -> {}
            }
            true
            }

        val btn = findViewById<Button>(R.id.btnEnableSms)



    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }


    //startsSmsServ
    fun startSmsService( ){
        this.registerReceiver(myBroadcastReceiver, filter)
        val intent = Intent()
        intent.setAction("com.example.mappe2.MITTSIGNAL")
        sendBroadcast(intent)
    }

    fun stopSmsService(){
        super.stopService(Intent(this, MinPeriodisk::class.java))
        super.stopService(Intent(this, MinSendService::class.java))
        this.unregisterReceiver(myBroadcastReceiver)
    }

    fun smsPermissionRequest() {
        val MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        val MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
        if (MY_PERMISSIONS_REQUEST_SEND_SMS === PackageManager.PERMISSION_GRANTED && MY_PHONE_STATE_PERMISSION === PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE),
                0
            )
        }
    }

}