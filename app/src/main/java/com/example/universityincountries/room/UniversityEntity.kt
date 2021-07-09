package com.example.universityincountries.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UniversityEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "state_province")
    val state_province: String?,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "alpha_two_code")
    val alpha_two_code: String


)