package com.example.universityincountries.model

import com.google.gson.annotations.SerializedName

data class University(
    val id:Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("state-province")
    val state_province: String?,
    @SerializedName("country")
    val country: String,
    @SerializedName("alpha_two_code")
    val alpha_two_code: String,



)