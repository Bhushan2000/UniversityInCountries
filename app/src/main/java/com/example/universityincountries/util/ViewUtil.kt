package com.example.mvvmapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


// It is extension function for Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

// It is extension function for ProgressBar
fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
    val connnectivityManager =
        activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connnectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}