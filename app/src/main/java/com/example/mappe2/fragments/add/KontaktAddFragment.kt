package com.example.mappe2.fragments.add

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
import com.example.mappe2.R
import com.example.mappe2.viewmodel.KontakViewModel
import com.example.mappe2.model.Kontakt
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


class KontaktAddFragment : Fragment() {

    private lateinit var kontaktViewModel: KontakViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kontakt_add, container, false)

        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonBack).setOnClickListener{
            findNavController().navigate(R.id.action_kontaktAddFragment_to_kontaktListFragment)
        }


        kontaktViewModel = ViewModelProvider(this).get(KontakViewModel::class.java)

        val navnListe = kontaktViewModel.navnListe

        val telefonListe = ArrayList<String>()

        view.findViewById<Button>(R.id.btnAddContact).setOnClickListener {
            insertDataToDatabase(navnListe, telefonListe)
        }


        return view
    }

    private fun insertDataToDatabase(navnListe: ArrayList<String>, telefonListe: ArrayList<String>) {


        val navn = view?.findViewById<EditText>(R.id.etNavn)?.text.toString()
        val telefon = view?.findViewById<EditText>(R.id.etTelefon)?.text.toString()

        //Creates User Object
        if (!isInvalid(navn, telefon) && !isDuplicate(navn, telefon, navnListe, telefonListe)){
            val kontakt = Kontakt(0, navn, telefon)
            //Add Data to Database
            kontaktViewModel.addKontakt(kontakt)
            navnListe.add(kontakt.navn.toString())
            telefonListe.add(kontakt.telefon.toString())
            Toast.makeText(requireContext(), "Navnliste: ${navnListe}" , Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_kontaktAddFragment_to_kontaktListFragment)
        }else{
            Toast.makeText(requireContext(),"Fyll ut alle feltene.", Toast.LENGTH_LONG).show()
        }


    }
    private fun isInvalid(navn: String, telefon: String): Boolean{
        return TextUtils.isEmpty(navn) || TextUtils.isEmpty(telefon)
    }

    private fun isDuplicate(navn: String, telefon: String, kontaktListe: ArrayList<String>, telefonListe: ArrayList<String>): Boolean{
        if (kontaktListe.contains(navn)){
            return true
            Toast.makeText(requireContext(),"Navnliste: ${kontaktListe.toString()}", Toast.LENGTH_LONG).show()
        }
        return false
    }

}