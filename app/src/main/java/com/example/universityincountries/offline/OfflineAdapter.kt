package com.example.universityincountries.offline

import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.universityincountries.R
import com.example.universityincountries.room.UniversityDatabase
import com.example.universityincountries.room.UniversityEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OfflineAdapter(val context: Context) :
    RecyclerView.Adapter<OfflineAdapter.OfflineViewHolder>() {

    var listOffline: MutableList<UniversityEntity> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_offline, parent, false)
        return OfflineViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfflineViewHolder, position: Int) {
        val model = listOffline[position]

        holder.name.text = model.name
        holder.code.text = "(" + model.alpha_two_code + ")"
        holder.state.text = model.state_province
        holder.country.text = model.country

        holder.delete.setOnClickListener {


            val university = UniversityEntity(
                position, model.name, model.state_province, model.country, model.alpha_two_code
            )
            deleteUniversity(model.id, holder.delete)
            listOffline.removeAt(position)



        }


    }

    private fun deleteUniversity(
        id: Int,
        floatingActionButton: FloatingActionButton
    ) {

        class SaveU : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                UniversityDatabase.getInstance(context).universityDAO.deleteUniversityById(id)

                return null

            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                Snackbar.make(floatingActionButton, "Deleted!!", Snackbar.LENGTH_SHORT).show()
                notifyDataSetChanged()

            }


        }

        SaveU().execute()
    }

    override fun getItemCount(): Int {
        return listOffline.size
    }

    class OfflineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvNameOffline)
        val code: TextView = view.findViewById(R.id.tvCodeOffline)
        val state: TextView = view.findViewById(R.id.tvStateOffline)
        val country: TextView = view.findViewById(R.id.tvCountryOffline)
        val delete: FloatingActionButton = view.findViewById(R.id.floatingActionButtonOffline)


    }

    fun setUList(uList: MutableList<UniversityEntity>) {
        this.listOffline = uList;
        notifyDataSetChanged()
    }
}