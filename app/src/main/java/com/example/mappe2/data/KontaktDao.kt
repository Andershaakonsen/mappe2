package com.example.mappe2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mappe2.model.Kontakt

//Data Access Object will containt the methods used for accesing the database
@Dao
interface KontaktDao {

    //Ignores if the user coming in is exaclty the same
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun addKontakt(kontakt: Kontakt)

    @Update
    fun updateKontakt(kontakt: Kontakt)

    @Delete
    fun deleteKontakt(kontakt: Kontakt)



    @Query("SELECT * FROM kontakt_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Kontakt>>

     
}