package com.example.mappe2.fragments.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mappe2.R
import com.example.mappe2.model.Avtale
import com.example.mappe2.viewmodel.AvtaleViewModel
import com.example.mappe2.viewmodel.KontakViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_avtale_add.*
import java.text.SimpleDateFormat
import java.util.*


class AvtaleAddFragment : Fragment() {

    private lateinit var mKontaktViewModel: KontakViewModel
    private lateinit var mAvtaleViewModel: AvtaleViewModel
    private lateinit var navneListe: Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_avtale_add, container, false)

        mAvtaleViewModel = ViewModelProvider(this).get(AvtaleViewModel::class.java)

        var navnArray: Array<String>
        val tvNavn = view.findViewById<TextView>(R.id.tvNavnListe)
        val tvDato = view.findViewById<TextView>(R.id.tvDisplayDato)
        val btnDatePicker = view.findViewById<Button>(R.id.btnDatePicker)
        val etTidspunkt = view.findViewById<EditText>(R.id.etTidspunkt)
        val etSted = view.findViewById<EditText>(R.id.etSted)
        val etKontakNavn = view.findViewById<EditText>(R.id.etKontaktNavn)
        val etMelding = view.findViewById<EditText>(R.id.etMelding)
        val btnAddAvtale = view.findViewById<Button>(R.id.btnAddAvtale)
        val btnBack = view.findViewById<FloatingActionButton>(R.id.floatingActionButtonBack)


        //var arr: Array<String> =

        //Genrates Kontakt Liste
        mKontaktViewModel = ViewModelProvider(this).get(KontakViewModel::class.java)
        mKontaktViewModel.readAllData.observe(viewLifecycleOwner, Observer { kontakt ->
            val liste = kontakt.map { T -> T.navn };
            navnArray = liste.toTypedArray()
            navneListe = navnArray
            tvNavn.setText("Kontakter: ${Arrays.toString(navnArray).replace("[", "").replace("]","")}")
            Toast.makeText(requireContext(),"${navnArray}" , Toast.LENGTH_LONG).show()
        })

        //Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val datePicker = DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(c)
        }

        //Date Dialog
        btnDatePicker.setOnClickListener {
            DatePickerDialog(requireContext(), datePicker, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
            Toast.makeText(requireContext(), "${c.time}", Toast.LENGTH_LONG).show()
        }

        //Database Insert
        btnAddAvtale.setOnClickListener {
            insertDataToDatabase(c.time, navneListe)
        }

        //Naviagte to avtaleList Fragment
        btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_avtaleAddFragment_to_avtaleListFragment)
        }






        return view
    }

    private fun updateLabel(c: Calendar) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
        tvDisplayDato.setText(sdf.format(c.time))
    }

    private fun insertDataToDatabase(date: Date, navnListe: Array<String>){


        //Avtale Object properties
        val sqlDato: java.sql.Date =  java.sql.Date(date.time)
        val klokkeslett = etTidspunkt.text.toString()
        val sted = etSted.text.toString()
        val melding = etMelding.text.toString()
        val navn = etKontaktNavn.text.toString()


        if(!isInvalid(klokkeslett, sted, navn, navnListe)){
            // Create Avtale Object
            val avtale = Avtale(0, sqlDato, klokkeslett, sted, melding, navn)
           // Add Avtale to Database
            mAvtaleViewModel.addAvtale(avtale)
            Toast.makeText(requireContext(), "${avtale} sendt til db", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_avtaleAddFragment_to_avtaleListFragment)
        }else Toast.makeText(requireContext(), "Venligst fyll ut Dato, Tidspunkt, Sted og Kontakt. NB! Kontakten må eksistere fra før av!", Toast.LENGTH_LONG).show()

    }

   private fun isInvalid(klokkeslett: String, sted: String, navn: String, navnListe: Array<String>): Boolean{

       return  TextUtils.isEmpty(tvDisplayDato.text.toString()) ||
               TextUtils.isEmpty(klokkeslett) ||
               TextUtils.isEmpty(sted) ||
               TextUtils.isEmpty(navn) ||
               !navnListe.contains(navn)
   }

}