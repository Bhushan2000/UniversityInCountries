package com.example.universityincountries.adapter

import android.content.Context
import android.opengl.Visibility
import android.os.AsyncTask


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import androidx.recyclerview.widget.RecyclerView
import com.example.universityincountries.R
import com.example.universityincountries.model.University
import com.example.universityincountries.offline.OfflineAdapter
import com.example.universityincountries.room.UniversityDatabase
import com.example.universityincountries.room.UniversityEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class UniversityAdapter(val context: Context) :
    RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder>() {

    var list: List<University> = listOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UniversityViewHolder(view)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val model = list[position]

        holder.name.text = model.name
        holder.state.text = model.state_province
        holder.code.text = "(" + model.alpha_two_code + ")"
        holder.country.text = model.country







            holder.add.setOnClickListener {
                var stateChecked: String? = null

                if (model.state_province != null) {
                    stateChecked = model.state_province
                } else {
                    stateChecked = "No Value"
                }

                val university = UniversityEntity(
                    model.id, model.name, stateChecked, model.country, model.alpha_two_code
                )

                try {

                    saveUniversity(university, holder.add)


                } catch (e: Exception) {
                    Snackbar.make(holder.add, "Added", Snackbar.LENGTH_SHORT).show()

                }


            }



    }

    private fun saveUniversity(
        university: UniversityEntity,

        floatingActionButton: FloatingActionButton
    ) {
        class SaveU : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {

                UniversityDatabase.getInstance(context).universityDAO.insertUniversity(university)


                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                Snackbar.make(floatingActionButton, "Added", Snackbar.LENGTH_SHORT).show()

            }


        }

        SaveU().execute()
    }



    override fun getItemCount(): Int {
        return list.size
    }

    class UniversityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val code: TextView = view.findViewById(R.id.tvCode)
        val state: TextView = view.findViewById(R.id.tvState)
        val country: TextView = view.findViewById(R.id.tvCountry)
        val add: FloatingActionButton = view.findViewById(R.id.floatingActionButton)


    }

    fun setUList(uList: List<University>) {
        this.list = uList;
        notifyDataSetChanged()
    }

}