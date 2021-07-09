package com.example.universityincountries.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UniversityEntity:: class] ,version = 1, exportSchema = true)
abstract class UniversityDatabase: RoomDatabase() {

    abstract val universityDAO: UniversityDAO

    // singleton is best practise
    companion object {
        @Volatile
        private var INSTANNCE: UniversityDatabase? = null

        fun getInstance(context: Context): UniversityDatabase {
            synchronized(this) {
                var instance: UniversityDatabase? = INSTANNCE
                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UniversityDatabase::class.java,
                        "UniversityEntity.db"
                    ).build()
                }
                return instance
            }

        }
    }
}