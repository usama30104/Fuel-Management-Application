package com.example.fuelmanagmentapplication.data
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fuelmanagmentapplication.domain.model.fuelexpensecalculation
import kotlinx.coroutines.flow.Flow
@Dao
interface FuelManagementDao {
    @Upsert
    suspend fun upsert(fuelexpensecalculation: fuelexpensecalculation)
    @Query("SELECT * FROM fuelexpensecalculation")
    fun getListOfFuelExpense(): Flow<List<fuelexpensecalculation>>
}