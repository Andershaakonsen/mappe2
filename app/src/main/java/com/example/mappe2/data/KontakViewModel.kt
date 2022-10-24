package com.example.mappe2.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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
}