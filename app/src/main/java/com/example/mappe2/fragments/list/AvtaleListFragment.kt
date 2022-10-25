package com.example.mappe2.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mappe2.R
import com.example.mappe2.viewmodel.KontakViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AvtaleListFragment : Fragment() {


    private lateinit var mKontaktViewModel: KontakViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_avtaler_list, container, false)

        view.findViewById<FloatingActionButton>(R.id.floatingBtnAddAvtale).setOnClickListener{
            findNavController().navigate(R.id.action_avtaleListFragment_to_avtaleAddFragment)
        }

        //List of names
        mKontaktViewModel = ViewModelProvider(this).get(KontakViewModel::class.java)

        return view
    }


}