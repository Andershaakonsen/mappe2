package com.example.mappe2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mappe2.databinding.ActivityMainBinding
import com.example.mappe2.fragments.Avtaler
import com.example.mappe2.fragments.Hjem
import com.example.mappe2.fragments.Kontakter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Hjem())

        //Sette rettigheter til å sende smser her

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.hjem -> replaceFragment(Hjem())
                R.id.kontakter -> replaceFragment(Kontakter())
                R.id.avtaler -> replaceFragment(Avtaler())
                else -> {}
            }
            true
            }

    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }


}