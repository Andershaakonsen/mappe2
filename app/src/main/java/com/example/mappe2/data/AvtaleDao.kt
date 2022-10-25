package com.example.mappe2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mappe2.model.Avtale
import com.example.mappe2.model.Kontakt

@Dao
interface AvtaleDao {
    //Ignores if the user coming in is exaclty the same
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun addAvtale(avtale: Avtale)

    @Update
    fun updateAvtale(avtale: Avtale)

    @Delete
    fun deleteAvtale(avtale: Avtale)

    @Query("SELECT * FROM avtale_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Avtale>>
}