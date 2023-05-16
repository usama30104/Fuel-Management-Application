package com.example.fuelmanagmentapplication.ui.activity
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.fuelmanagmentapplication.databinding.ActivityFuelExpenseCalculationBinding
import com.example.fuelmanagmentapplication.viewmodel.FuelExpenseViewModel
class FuelExpenseCalculation : AppCompatActivity() {
    private lateinit var binding: ActivityFuelExpenseCalculationBinding
    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFuelExpenseCalculationBinding.inflate(layoutInflater)
        // binding.root returns the root layout,
        // which is activity_main.xml file itself
        setContentView(binding.root)
//            startActivity(Intent(this@FuelExpenseCalculation, ))
//        }
        val fuelExpenseViewModel =
            ViewModelProvider(this)[FuelExpenseViewModel::class.java]//object of view model to access
        val fuelprice = binding.fuelPrice.editText
        val vehicleAverage = binding.VehicleAverage.editText
        val distanceTravel = binding.distanceTravel.editText

        fuelprice?.addTextChangedListener {
            if (it.toString().isNotBlank()) fuelExpenseViewModel.fuelprice = it.toString().toFloat()
        }
        vehicleAverage?.addTextChangedListener {
            if (it.toString().isNotBlank()) fuelExpenseViewModel.vehicleAverage =
                it.toString().toFloat()
        }
        binding.fuelBtnCal.setOnClickListener {
            if (fuelprice!!.text.isNotBlank() && vehicleAverage!!.text.isNotBlank() && distanceTravel!!.text.isNotBlank()) {
                fuelExpenseViewModel.calculateFuel()
                fuelExpenseViewModel.insertIntoDatabase(this)
            }
            else {
                Toast.makeText(this, "Please Fill all text First", Toast.LENGTH_LONG).show()
            }
            //  Log.i( "onCreate: ", "clicked")
        }
        fuelExpenseViewModel.totalCost.observe(this) { it ->
            "Total Cost of Fuel: $it".also { binding.textViewFuelExpense.text = it }
        }
        distanceTravel?.addTextChangedListener {
            if (it.toString().isNotEmpty()) fuelExpenseViewModel.distanceTravel =
                it.toString().toFloat()
        }
    }
}