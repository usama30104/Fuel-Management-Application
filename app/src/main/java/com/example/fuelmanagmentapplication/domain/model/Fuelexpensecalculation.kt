package com.example.fuelmanagmentapplication.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class fuelexpensecalculation(
    val fuelPrice: Float,
    val VehicleAverage: Float,
    val totalDistance: Float,
    val totalCalculation: Float,
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null
)
