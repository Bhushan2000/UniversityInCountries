package com.example.universityincountries.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universityincountries.University
import okhttp3.ResponseBody
import retrofit2.Response

@Dao
interface UniversityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUniversity(entity: UniversityEntity)

    @Query("SELECT * FROM UniversityEntity")
   suspend fun getData(): List<UniversityEntity>

}