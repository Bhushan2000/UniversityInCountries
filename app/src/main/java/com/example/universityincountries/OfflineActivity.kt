package com.example.universityincountries

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.universityincountries.room.UniversityDatabase
import kotlinx.coroutines.launch

class OfflineActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var universityAdapter: OfflineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline)

        recyclerView = findViewById(R.id.recyclerView2)
         universityAdapter = OfflineAdapter( this)

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

    private suspend fun getData()  {
        val  respose = UniversityDatabase.getInstance(this).universityDAO.getData()



         universityAdapter.setUList(respose)




    }


}