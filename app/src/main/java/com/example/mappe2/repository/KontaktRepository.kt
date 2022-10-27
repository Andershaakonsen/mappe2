package com.example.mappe2.repository

import androidx.lifecycle.LiveData
import com.example.mappe2.data.KontaktDao
import com.example.mappe2.model.Kontakt

class KontaktRepository(private val kontaktDao: KontaktDao) {

    val readAllData: LiveData<List<Kontakt>> = kontaktDao.readAllData()

    fun addKontakt(kontakt: Kontakt){
        kontaktDao.addKontakt(kontakt)
    }

    fun updateKontakt(kontakt: Kontakt){
        kontaktDao.updateKontakt(kontakt)
    }

    fun deleteKontakt(kontakt: Kontakt){
        kontaktDao.deleteKontakt(kontakt)
    }

    /*
    suspend fun kontaktExists(kontakt: Kontakt): List<Kontakt> {
        return kontaktDao.kontaktExists()
    }

     */

}