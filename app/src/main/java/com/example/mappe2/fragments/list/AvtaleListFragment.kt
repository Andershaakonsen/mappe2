package com.example.mappe2.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mappe2.R
import com.example.mappe2.viewmodel.AvtaleViewModel
import com.example.mappe2.viewmodel.KontakViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AvtaleListFragment : Fragment() {


    private lateinit var mKontaktViewModel: KontakViewModel
    private lateinit var mAvtaleViewModel: AvtaleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_avtaler_list, container, false)

        view.findViewById<FloatingActionButton>(R.id.floatingBtnAddAvtale).setOnClickListener{
            findNavController().navigate(R.id.action_avtaleListFragment_to_avtaleAddFragment)
        }

        //Recyclerview
        val adapter = AvtaleListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvAvtaleListe)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //AvtaleViewModel
        mAvtaleViewModel = ViewModelProvider(this).get(AvtaleViewModel::class.java)
        //Using observer to change data when sql data changes
        mAvtaleViewModel.readAllData.observe(viewLifecycleOwner, Observer { avtale ->
            adapter.setData(avtale)
        })

        //List of names
        mKontaktViewModel = ViewModelProvider(this).get(KontakViewModel::class.java)

        return view
    }


}