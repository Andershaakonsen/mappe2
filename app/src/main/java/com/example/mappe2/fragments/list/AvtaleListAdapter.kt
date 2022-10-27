package com.example.mappe2.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mappe2.R
import com.example.mappe2.model.Avtale
import kotlinx.android.synthetic.main.custom_row_avtale.view.*
import kotlinx.android.synthetic.main.fragment_avtale_update.view.*
import java.text.SimpleDateFormat
import java.util.*

class AvtaleListAdapter: RecyclerView.Adapter<AvtaleListAdapter.MyViewHolder> (){

    private var avtaleList = emptyList<Avtale>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_avtale, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = avtaleList[position]


        updateLabel(currentItem.dato, holder.itemView.tvDatoAvtale)
        holder.itemView.tvTidspunktAvtale.text = currentItem.klokkeslett
        holder.itemView.tvStedAvtale.text = currentItem.sted
        holder.itemView.tvMeldingAvtale.text = currentItem.melding
        holder.itemView.tvNavnAvtale.text = currentItem.navn

        //Navigates to update fragment and passes avtale object via safeargs
        holder.itemView.rowLayoutAvtale.setOnClickListener{
            val action = AvtaleListFragmentDirections.actionAvtaleListFragmentToAvtaleUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setData(avtale: List<Avtale>){
        this.avtaleList = avtale
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return avtaleList.size
    }

    //Formats date
    private fun updateLabel(date: Date, displayDato: TextView) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
        displayDato.setText(sdf.format(date))
    }

}