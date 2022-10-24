package com.example.mappe2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Data Access Object will containt the methods used for accesing the database
@Dao
interface KontaktDao {

    //Ignores if the user coming in is exaclty the same
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun addKontakt(kontakt: Kontakt)

    @Query("SELECT * FROM kontakt_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Kontakt>>

     
}