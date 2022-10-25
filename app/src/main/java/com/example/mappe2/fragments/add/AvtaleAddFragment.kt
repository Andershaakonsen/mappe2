package com.example.mappe2.fragments.add

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mappe2.R
import com.example.mappe2.viewmodel.KontakViewModel
import kotlinx.android.synthetic.main.fragment_avtale_add.*
import kotlinx.android.synthetic.main.fragment_avtale_add.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AvtaleAddFragment : Fragment() {

    private lateinit var mKontaktViewModel: KontakViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_avtale_add, container, false)

        var navnArray: Array<String>
        val tvNavn = view.findViewById<TextView>(R.id.tvNavn)
        val tvDato = view.findViewById<TextView>(R.id.tvDato)
        val btnDatePicker = view.findViewById<Button>(R.id.btnDatePicker)



        //Genrates Kontakt Liste
        var navnListe: Array<String>
        mKontaktViewModel = ViewModelProvider(this).get(KontakViewModel::class.java)
        mKontaktViewModel.readAllData.observe(viewLifecycleOwner, Observer { kontakt ->
            val liste = kontakt.map { T -> T.navn };
            navnArray = liste.toTypedArray()
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

        btnDatePicker.setOnClickListener {
            DatePickerDialog(requireContext(), datePicker, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
            Toast.makeText(requireContext(), "${c.time}", Toast.LENGTH_LONG).show()
        }



        return view
    }

    private fun updateLabel(c: Calendar) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
        tvDato.setText(sdf.format(c.time))
    }


}