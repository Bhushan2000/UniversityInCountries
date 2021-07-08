package com.example.universityincountries

import android.content.Context
import android.os.AsyncTask


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


import androidx.recyclerview.widget.RecyclerView
import androidx.room.CoroutinesRoom.Companion.execute
import com.example.universityincountries.room.UniversityDatabase
import com.example.universityincountries.room.UniversityEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

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
        holder.code.text = "(" + model.alpha_two_code + ")"
        holder.state.text = model.state_province
        holder.country.text = model.country


        holder.add.setOnClickListener {


            val university = UniversityEntity(
                position, model.name, model.state_province, model.country, model.alpha_two_code
            )
            saveUniversity(university, holder.add)

        }


    }

    fun saveUniversity(university: UniversityEntity, floatingActionButton: FloatingActionButton) {
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
//    fun addData(listItems:List<University>){
//        var size = this.listItems.size
//        this.listItems.addAll(listItems)
//        var sizeNew = this.listItems.size
//        notifyItemRangeChanged(size,sizeNew)
//    }
}