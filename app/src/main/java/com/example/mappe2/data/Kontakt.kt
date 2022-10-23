package com.example.mappe2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kontakt_table")
data class Kontakt(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val navn: String,
    val telefon: String
) {

}