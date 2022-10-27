package com.example.mappe2.fragments.update

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mappe2.R
import com.example.mappe2.model.Avtale
import com.example.mappe2.viewmodel.AvtaleViewModel
import com.example.mappe2.viewmodel.KontakViewModel
import kotlinx.android.synthetic.main.fragment_avtale_add.*
import kotlinx.android.synthetic.main.fragment_avtale_update.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


class AvtaleUpdateFragment : Fragment() {

    private val args by navArgs<AvtaleUpdateFragmentArgs>()
    private lateinit var mKontaktViewModel: KontakViewModel
    private lateinit var mAvtaleViewModel: AvtaleViewModel
    private lateinit var navneListe: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_avtale_update, container, false)


        val tvUpdateDisplayDato = view.findViewById<TextView>(R.id.tvUpdateDisplayDato)
        val dato = args.currentAvtale.dato
        //Calendar
        val c = Calendar.getInstance()
        c.time = dato;
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        updateLabel(c, tvUpdateDisplayDato)

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }



        //Date Dialog
        val btnDatePicker = view.findViewById<Button>(R.id.btnUpdateDatePicker)
        btnDatePicker.setOnClickListener {
            DatePickerDialog(requireContext(), datePicker, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(
                Calendar.DAY_OF_MONTH)).show()
            Toast.makeText(requireContext(), "${c.time}", Toast.LENGTH_LONG).show()
        }


        var navnArray: Array<String>
        //Generate kontakt liste
        mKontaktViewModel = ViewModelProvider(this).get(KontakViewModel::class.java)
        mKontaktViewModel.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {kontakt ->
            val liste = kontakt.map { T -> T.navn }
            navnArray = liste.toTypedArray()
            navneListe = navnArray
            tvUpdateNavnListe.setText("Kontakter: ${Arrays.toString(navnArray).replace("[", "").replace("]","")}")
        })




        //Sets Text from Arguments
        view.findViewById<EditText>(R.id.etUpdateTidspunkt).setText(args.currentAvtale.klokkeslett)
        view.findViewById<EditText>(R.id.etUpdateSted).setText(args.currentAvtale.sted)
        view.findViewById<EditText>(R.id.etUpdateKontaktNavn).setText(args.currentAvtale.navn)
        view.findViewById<EditText>(R.id.etUpdateMelding).setText(args.currentAvtale.melding)


        return view
    }

    //Formats time
    private fun updateLabel(c: Calendar, displayDato: TextView) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
        displayDato.setText(sdf.format(c.time))
    }

    private fun updateAvtale(date: Date, navnListe: Array<String>){

        //Avtake Object properties
        val sqlDato: java.sql.Date = java.sql.Date(date.time)
        val klokkeslett = etUpdateTidspunkt.text.toString()
        val sted = etUpdateSted.text.toString()
        val melding = etUpdateMelding.text.toString()
        val navn = etUpdateKontaktNavn.text.toString()

        if(!isInvalid(klokkeslett, sted, navn, navnListe)){
            //Create Avtale Object
            val avtale = Avtale(0, sqlDato, klokkeslett, sted,melding,navn)
            //Add Avtale to DB
            mAvtaleViewModel.updateAvtale(avtale)
            //Navigate back
            findNavController().navigate(R.id.action_avtaleUpdateFragment_to_avtaleListFragment)
        }else Toast.makeText(requireContext(),
            "Venligst fyll ut Dato, Tidspunkt, Sted og Kontakt. NB! Kontakten må eksistere fra før av!",
            Toast.LENGTH_LONG).show()
    }

    private fun isInvalid(klokkeslett: String, sted: String, navn: String, navnListe: Array<String>): Boolean{

        return  TextUtils.isEmpty(tvDisplayDato.text.toString()) ||
                TextUtils.isEmpty(klokkeslett) ||
                TextUtils.isEmpty(sted) ||
                TextUtils.isEmpty(navn) ||
                !navnListe.contains(navn)
    }

}