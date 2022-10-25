package com.example.mappe2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//Parcelize to send object through safeArgs to update fragment
@Parcelize
@Entity(tableName = "kontakt_table")
data class Kontakt(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val navn: String,
    val telefon: String
): Parcelable