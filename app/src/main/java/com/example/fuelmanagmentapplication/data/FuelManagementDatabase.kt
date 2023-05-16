package com.example.fuelmanagmentapplication.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fuelmanagmentapplication.domain.model.FuelEfficiencyCalculation
import com.example.fuelmanagmentapplication.domain.model.fuelexpensecalculation
@Database(
    entities = [fuelexpensecalculation::class, FuelEfficiencyCalculation::class],
    version = 1
)
abstract class FuelManagementDatabase :RoomDatabase() {
    abstract val dao: FuelManagementDao
    abstract val daoefficiecny: FuelEfficiencyDao
    companion object {
        @Volatile
        private var instance: FuelManagementDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FuelManagementDatabase::class.java,
                "article_db.db"
            ).build()
    }
}