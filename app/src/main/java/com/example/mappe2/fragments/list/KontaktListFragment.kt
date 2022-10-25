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
import com.example.mappe2.viewmodel.KontakViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class KontaktListFragment : Fragment() {

    private lateinit var mKontaktViewModel: KontakViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kontakt_list, container, false)

        // Recyclerview
        val adapter = KontaktListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvKontaktListe)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //KontakViewModel
        mKontaktViewModel = ViewModelProvider(this).get(KontakViewModel::class.java)
        mKontaktViewModel.readAllData.observe(viewLifecycleOwner, Observer { kontakt ->
            adapter.setData(kontakt)
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonAdd).setOnClickListener{
            findNavController().navigate(R.id.action_kontaktListFragment_to_kontaktAddFragment)
        }


        return view
    }


}