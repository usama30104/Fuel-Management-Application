package com.example.fuelmanagmentapplication.viewmodel
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuelmanagmentapplication.data.FuelManagementDatabase
import com.example.fuelmanagmentapplication.domain.model.fuelexpensecalculation
import kotlinx.coroutines.launch
class FuelExpenseViewModel : ViewModel() {
    var fuelprice = 0f
    var vehicleAverage = 0f
    var distanceTravel = 0f
    var totalCost = MutableLiveData(0f)
    var perKm = 1f
    fun calculateFuel() {
//      Log.i("calculateFuel: ", "$perKm")
//        Log.i("calculateFuel: ", "$perLiter")
        val perLiter =/* perKm!!*/(perKm / vehicleAverage) * distanceTravel
        totalCost.value = perLiter * fuelprice
    }
    fun insertIntoDatabase(context: Context) {
        viewModelScope.launch {
            val fuelexpensecalculation =
                fuelexpensecalculation(fuelprice, vehicleAverage, distanceTravel, totalCost.value!!)
            FuelManagementDatabase.invoke(context)
                .dao.upsert(fuelexpensecalculation)
        }
    }
}