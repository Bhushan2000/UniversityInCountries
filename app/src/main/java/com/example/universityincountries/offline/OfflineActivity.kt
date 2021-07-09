package com.example.universityincountries.offline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.util.verifyAvailableNetwork
import com.example.universityincountries.R
import com.example.universityincountries.room.UniversityDatabase
import com.example.universityincountries.room.UniversityEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class OfflineActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var universityAdapter: OfflineAdapter
    lateinit var tvNoNetworkOffline:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline)
        tvNoNetworkOffline =  findViewById(R.id.tvNoNetworkOffline)
        recyclerView = findViewById(R.id.recyclerView2)
        universityAdapter = OfflineAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = universityAdapter




        actionBar?.setDisplayHomeAsUpEnabled(true)




        lifecycleScope.launch {

            getData()

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == android.R.id.home) {
            this.finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private suspend fun getData() {
        val respose = UniversityDatabase.getInstance(this).universityDAO.getData()


        universityAdapter.setUList(respose)

        if (universityAdapter.itemCount==0  ){

            Snackbar.make(recyclerView, "No Data found!!", Snackbar.LENGTH_SHORT).show()

        }

        if (!verifyAvailableNetwork(this)){
            tvNoNetworkOffline.visibility = View.VISIBLE
        }


    }


}