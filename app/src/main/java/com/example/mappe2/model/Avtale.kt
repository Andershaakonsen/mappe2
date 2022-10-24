package com.example.mappe2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Parcelize
@Entity(tableName = "avtale_table")
@TypeConverters(DateConverter::class)
data class Avtale(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val klokkeslett: String,
    val sted: String,
    val melding: String,
    val navn: String): Parcelable

