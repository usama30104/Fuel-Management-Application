package com.example.fuelmanagmentapplication.viewmodel
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuelmanagmentapplication.data.FuelManagementDatabase
import com.example.fuelmanagmentapplication.domain.model.FuelEfficiencyCalculation
import kotlinx.coroutines.launch
class FuelEfficiencyViewModel : ViewModel() {
    var initialFuel= 0f
    var initialReading = 0f
    var petrolPriceAtRefil = 0f
    var fuelDifference = 0f
    var finalReading = 0f
    var fuelPriceAtEnd = 0f
    var readingDifference = 0f
    var result = MutableLiveData("")
    fun efficiencyHealth() {
        readingDifference = initialReading - finalReading
        fuelDifference = petrolPriceAtRefil - fuelPriceAtEnd
        result.value = "Average of your vehicle is : ${readingDifference / initialFuel}"
//        Log.i( "efficiencyHealth: ", "readingDifference: $readingDifference")
//        Log.i( "efficiencyHealth: ", "readingDifference: $readingDifference") //result.value = "Average of your car is : ${readingDifference / readingDifference}"
//        Log.i( "efficiencyHealth: ", "result: $result") //   val totalAverage = petrolInLiters * initialReading
    }
    fun EfficiencyInsertIntoDatabase(context: Context) {
        viewModelScope.launch {
            val FuelEfficiencyCalculation = FuelEfficiencyCalculation(
               // fuelPrice, initialReading, fuelPrice, result.value!!
          initialFuel=  initialFuel,
                initialReading= initialReading,
                petrolPriceAtRefil = petrolPriceAtRefil,
                fuelUsed = initialFuel,
                finalReading = finalReading,
                fuelPriceAtEnd = fuelPriceAtEnd,
                result = result.value!!,
                fuelDifference = fuelDifference
            )
            FuelManagementDatabase.invoke(context).daoefficiecny.upsert(FuelEfficiencyCalculation)
        }
    }
}