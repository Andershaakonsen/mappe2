package com.example.mappe2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mappe2.data.AppDatabase
import com.example.mappe2.repository.KontaktRepository
import com.example.mappe2.model.Kontakt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class KontakViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Kontakt>>
    private val repository: KontaktRepository

    init {
        val kontaktDao = AppDatabase.getDatabase(application).kontaktDao()
        repository = KontaktRepository(kontaktDao)
        readAllData = repository.readAllData
    }

    fun addKontakt(kontakt: Kontakt){
        viewModelScope.launch(Dispatchers.IO){
            repository.addKontakt(kontakt)
        }
    }

    //Dispatcher runs from background thread
    fun updateKontakt(kontakt: Kontakt){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateKontakt(kontakt)
        }
    }

    fun deleteKontakt(kontakt: Kontakt){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteKontakt(kontakt)
        }
    }


}