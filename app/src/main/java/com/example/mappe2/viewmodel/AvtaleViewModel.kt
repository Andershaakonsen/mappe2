package com.example.mappe2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mappe2.data.AppDatabase
import com.example.mappe2.model.Avtale
import com.example.mappe2.repository.AvtaleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AvtaleViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Avtale>>
    private val repository: AvtaleRepository
    init {
        val avtaleDao = AppDatabase.getDatabase(application).avtaleDao()
        repository = AvtaleRepository(avtaleDao)
        readAllData = repository.readAllData
       // val readAllDataToday: List<Avtale> = repository.readAllDataToday
    }

    fun addAvtale(avtale: Avtale){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAvtale(avtale)
        }
    }

    fun updateAvtale(avtale: Avtale){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateAvtale(avtale)
        }
    }
    fun deleteAvtale(avtale: Avtale){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAvtale(avtale)
        }
    }
}