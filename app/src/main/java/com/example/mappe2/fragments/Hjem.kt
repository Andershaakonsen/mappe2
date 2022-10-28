package com.example.mappe2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.mappe2.MainActivity
import com.example.mappe2.R

class Hjem : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hjem, container, false)

        val btnEnable = view.findViewById<Button>(R.id.btnEnableSms)
        //Calls startService method in mainActivity
        btnEnable.setOnClickListener {
            (activity as MainActivity)!!.startSmsService()
            view.findViewById<TextView>(R.id.tvServiceDisplay).text = "AKTIVERT"
        }

        val btnDisable = view.findViewById<Button>(R.id.btnDisableService)
        btnDisable.setOnClickListener {
            (activity as MainActivity)!!.stopSmsService()
            view.findViewById<TextView>(R.id.tvServiceDisplay).text = "DEAKTIVERT"
        }


        return view
    }



}