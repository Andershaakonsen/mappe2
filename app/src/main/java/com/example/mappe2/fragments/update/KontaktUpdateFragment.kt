package com.example.mappe2.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
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


    //Gets safeargs from RecyclerView Kontakt Object
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

        //Add menu
        setHasOptionsMenu(true)


        return view
    }


    private fun updateItem(){
        val navn = etUpdateNavn.text.toString()
        val telefon = etUpdateTelefon.text.toString()

        if(!isInvalid(navn, telefon)){
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

    private fun isInvalid(navn: String, telefon: String): Boolean{
        return TextUtils.isEmpty(navn) || TextUtils.isEmpty(telefon)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    //Displays popup if clicked on
    private fun deleteUser() {
       val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mKontakViewModel.deleteKontakt(args.currentKontakt)
            Toast.makeText(
                requireContext(),
                "Kontakt fjernet: ${args.currentKontakt.navn}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_kontaktUpdateFragment_to_kontaktListFragment)
        }
        builder.setNegativeButton("NO"){_, _ ->}
        builder.setTitle("Slett ${args.currentKontakt.navn}")
        builder.setMessage("Vil du slette ${args.currentKontakt.navn} fra kontakt listen?")
        builder.create().show()
    }
}