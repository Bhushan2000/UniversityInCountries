package com.example.mvvmapp.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast


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
