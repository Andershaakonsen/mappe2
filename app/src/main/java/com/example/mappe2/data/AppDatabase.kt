package com.example.mappe2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mappe2.model.Avtale
import com.example.mappe2.model.Kontakt


@Database(entities = arrayOf(Kontakt::class, Avtale::class), version = 3, exportSchema = false)
public abstract class AppDatabase: RoomDatabase() {

    abstract fun kontaktDao(): KontaktDao
    abstract fun avtaleDao(): AvtaleDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "word_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance

            }
        }
    }
}