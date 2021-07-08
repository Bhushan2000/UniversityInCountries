package com.example.universityincountries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.universityincountries.room.UniversityEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OfflineAdapter(val context: Context):   RecyclerView.Adapter<OfflineAdapter.OfflineViewHolder >()  {

    var listOffline: List<UniversityEntity> = listOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_offline, parent, false)
        return OfflineViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfflineViewHolder, position: Int) {
        val model = listOffline[position]

        holder.name.text = model.name
        holder.code.text = "(" + model.alpha_two_code + ")"
        holder.state.text = model.state_province
        holder.country.text = model.country

    }

    override fun getItemCount(): Int {
       return listOffline.size
    }
    class OfflineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvNameOffline)
        val code: TextView = view.findViewById(R.id.tvCodeOffline)
        val state: TextView = view.findViewById(R.id.tvStateOffline)
        val country: TextView = view.findViewById(R.id.tvCountryOffline)


    }
    fun setUList(uList: List<UniversityEntity>) {
        this.listOffline = uList;
        notifyDataSetChanged()
    }
}