package com.example.mappe2.data

import androidx.lifecycle.LiveData
import com.example.mappe2.model.Kontakt

class KontaktRepository(private val kontaktDao: KontaktDao) {

    val readAllData: LiveData<List<Kontakt>> = kontaktDao.readAllData()

    suspend fun addKontakt(kontakt: Kontakt){
        kontaktDao.addKontakt(kontakt)
    }
}