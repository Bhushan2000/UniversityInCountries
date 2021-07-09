package com.example.universityincountries


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.util.verifyAvailableNetwork
import com.example.universityincountries.adapter.UniversityAdapter
import com.example.universityincountries.offline.OfflineActivity
import com.example.universityincountries.network.ApiService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var universityAdapter: UniversityAdapter

    lateinit var relativeLayout: RelativeLayout
    lateinit var tvNoNetwork: TextView

    lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        tvNoNetwork = findViewById(R.id.tvNoNetwork)
        recyclerView = findViewById(R.id.recyclerView)
        relativeLayout = findViewById(R.id.relativeLayout)
        universityAdapter = UniversityAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = universityAdapter


        // coroutine scope
        // here we are using lifecycleScope Whatever coroutine not inside this code automatic cancelled
        // when our activity lifecycle is destroy do  not worry about memory leaks or other stuff.


        val check = verifyAvailableNetwork(this)
        if (check) {
            lifecycleScope.launch {

                getUData()
            }

        } else {
            Snackbar.make(recyclerView, "Check internet!!", Snackbar.LENGTH_SHORT).show()
            relativeLayout.visibility = View.GONE
            tvNoNetwork.visibility = View.VISIBLE
        }


    }

    // //      without coroutine
//    private fun getUData() {
//
//
//        ApiService().getUniversitiesData().enqueue(object : Callback<List<University>> {
//            override fun onResponse(
//                call: Call<List<University>>,
//                response: Response<List<University>>
//            ) {
//                try {
//
//                    if (response.isSuccessful) {
//                        relativeLayout.visibility = View.GONE
//                        recyclerView.apply {
//                            setHasFixedSize(true)
//                            layoutManager = LinearLayoutManager(this@MainActivity)
//                            tvOutput.visibility = View.GONE
//                            universityAdapter.setUList(response.body()!!)
//
//
//                        }
//                    }
//                } catch (e: Exception) {
//                    toast(e.toString())
//                    tvOutput.text = e.toString()
//                }
//            }
//
//            override fun onFailure(call: Call<List<University>>, t: Throwable) {
//                tvOutput.text = t.toString()
//            }
//
//        })
//
//    }


    // //      with coroutine
    private suspend fun getUData() {
// Suspend function 'getUniversitiesData' should be called only from a coroutine or another suspend function
// if you define function suspend we cannot execute it
// only  execute from coroutine scope
// Coroutine is a thread

        val response = ApiService().getUniversitiesData()
        relativeLayout.visibility = View.GONE
        universityAdapter.setUList(response.body()!!)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if (id == R.id.menuOffline) {
            startActivity(
                Intent(
                    this@MainActivity,
                    OfflineActivity::class.java
                )
            )


        }

        return super.onOptionsItemSelected(item)
    }





}