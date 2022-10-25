package com.example.mappe2.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mappe2.R
import com.example.mappe2.model.Kontakt
import kotlinx.android.synthetic.main.custom_row.view.*


class KontaktListAdapter: RecyclerView.Adapter<KontaktListAdapter.MyViewHolder>() {

    private var kontaktList = emptyList<Kontakt>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return kontaktList.size
    }

    //Setting textviews text inside custom row
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentItem = kontaktList[position]
        holder.itemView.findViewById<TextView>(R.id.tvKontaktNavn).text = currentItem.navn
        holder.itemView.findViewById<TextView>(R.id.tvKontaktTelefon).text = currentItem.telefon

        //Sends contact to update fragment
        holder.itemView.rowLayout.setOnClickListener{
            val action = KontaktListFragmentDirections.actionKontaktListFragmentToKontaktUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(kontakt: List<Kontakt>){
        this.kontaktList = kontakt
        notifyDataSetChanged()
    }
}