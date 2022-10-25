package com.example.mappe2.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mappe2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_avtaler_list.view.*

class AvtaleListFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_avtaler_list, container, false)

        view.findViewById<FloatingActionButton>(R.id.floatingBtnAddAvtale).setOnClickListener{
            findNavController().navigate(R.id.action_avtaleListFragment_to_avtaleAddFragment)
        }

        return view
    }


}