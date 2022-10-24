package com.example.mappe2.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mappe2.R
import com.example.mappe2.model.Kontakt
import com.example.mappe2.viewmodel.KontakViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_kontakt_update.*

class KontaktUpdateFragment : Fragment() {


    private val args by navArgs<KontaktUpdateFragmentArgs>()
    private lateinit var mKontakViewModel: KontakViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kontakt_update, container, false)

        mKontakViewModel = ViewModelProvider(this).get(KontakViewModel::class.java)

        //Back Button
        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonBack).setOnClickListener{
            findNavController().navigate(R.id.action_kontaktUpdateFragment_to_kontaktListFragment)
        }

        //Sets Text from Arguments
        view.findViewById<EditText>(R.id.etUpdateNavn).setText(args.currentKontakt.navn)
        view.findViewById<EditText>(R.id.etUpdateTelefon).setText(args.currentKontakt.telefon)

        view.findViewById<Button>(R.id.btnUdapteContact).setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem(){
        val navn = etUpdateNavn.text.toString()
        val telefon = etUpdateTelefon.text.toString()

        if(inputCheck(navn, telefon)){
            //Create Kontak Object
            val updatedKontakt = Kontakt(args.currentKontakt.id, navn, telefon)
            //Update Current Kontakt
            mKontakViewModel.updateKontakt(updatedKontakt)
            //Navigate back
            findNavController().navigate(R.id.action_kontaktUpdateFragment_to_kontaktListFragment)
        }else{
            Toast.makeText(requireContext(), "Fyll ut alle feltene", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(navn: String, telefon: String): Boolean{
        return !(TextUtils.isEmpty(navn) && TextUtils.isEmpty(telefon))
    }

}