package com.example.universityincountries

import com.google.gson.annotations.SerializedName

data class University(
    @SerializedName("name")
    val name: String,
    @SerializedName("state-province")
    val state_province: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("alpha_two_code")
    val alpha_two_code: String,



)