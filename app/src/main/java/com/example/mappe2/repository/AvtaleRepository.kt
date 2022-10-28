package com.example.mappe2.repository

import androidx.lifecycle.LiveData
import com.example.mappe2.data.AvtaleDao
import com.example.mappe2.model.Avtale

class AvtaleRepository(private val avtaleDao: AvtaleDao) {

    val readAllData: LiveData<List<Avtale>> = avtaleDao.readAllData()
    //val readAllDataToday: List<Avtale> = avtaleDao.readAllDataToday()

    suspend fun addAvtale(avtale: Avtale){
        avtaleDao.addAvtale(avtale)
    }

    suspend fun updateAvtale(avtale: Avtale){
        avtaleDao.updateAvtale(avtale)
    }

    suspend fun deleteAvtale(avtale: Avtale){
        avtaleDao.deleteAvtale(avtale)
    }


}

