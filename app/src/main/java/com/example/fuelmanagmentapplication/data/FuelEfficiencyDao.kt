package com.example.fuelmanagmentapplication.data
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fuelmanagmentapplication.domain.model.FuelEfficiencyCalculation
import kotlinx.coroutines.flow.Flow
@Dao
interface FuelEfficiencyDao {
    @Upsert
    suspend fun upsert(FuelEfficiencyCalculation: FuelEfficiencyCalculation)
    @Query("SELECT * FROM FuelEfficiencyCalculation")
    fun getListOfFuelEfficiency(): Flow<List<FuelEfficiencyCalculation>>
}