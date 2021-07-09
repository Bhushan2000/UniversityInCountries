package com.example.universityincountries.room

import androidx.room.*
import java.text.FieldPosition

@Dao
interface UniversityDAO {
    @Insert
    fun insertUniversity(entity: UniversityEntity)

    @Query("SELECT * FROM UniversityEntity")
    suspend fun getData(): MutableList<UniversityEntity>
    
    @Delete
    fun deleteUniversity(entity: UniversityEntity)

    @Query("DELETE FROM UniversityEntity WHERE id = :uid")
    fun deleteUniversityById(uid: Int)

}