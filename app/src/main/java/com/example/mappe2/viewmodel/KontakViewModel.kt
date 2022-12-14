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

/*
The Viewmodels role is to provide data to the UI and survive configuration changes.
A ViewModel acts as a communication center between the Repository and the UI
 */

class KontakViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Kontakt>>
    private val repository: KontaktRepository

    val navnListe: ArrayList<String>

    init {
        val kontaktDao = AppDatabase.getDatabase(application).kontaktDao()
        repository = KontaktRepository(kontaktDao)
        readAllData = repository.readAllData
        navnListe = ArrayList<String>()
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

    /*
    suspend fun kontaktExists(kontakt: Kontakt): List<Kontakt> {
       return repository.kontaktExists(kontakt)
    }


     */


}