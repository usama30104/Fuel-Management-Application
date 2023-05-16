package com.example.fuelmanagmentapplication.ui.activity
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.fuelmanagmentapplication.databinding.ActivityFuelEfficiencyBinding
import com.example.fuelmanagmentapplication.viewmodel.FuelEfficiencyViewModel
class FuelEfficiency : AppCompatActivity() {
    private lateinit var binding: ActivityFuelEfficiencyBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFuelEfficiencyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tankSize.editTextField.hint = "Fuel In Liters"
        binding.average.editTextField.hint = "Initial Meter Reading"
        //binding.totalTank.editTextField.hint = "Total Travel in Single Tank"
       // binding.yourMileageRange.editTextField.hint = "Your Mileage in a Tank "
        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        var check: Boolean = sharedPref.getBoolean("flag", true)
        if (check){
            binding.tankSize.editTextField.hint = "Total Liters of Fuel"
            binding.average.editTextField.hint = "Initial Meter Reading"
            binding.totalTank.editTextField.hint = "Fuel Price At start"
            binding.fuelEfficiencyBtn.text = "save"
        }
        else{
            binding.tankSize.editTextField.hint = "Used Fuel"
            binding.average.editTextField.hint = "Final Meter Reading"
            binding.totalTank.editTextField.hint = "Fuel Price At End"
            binding.fuelEfficiencyBtn.text = "Calculate"
        }
        val fuelEfficiencyViewModel = ViewModelProvider(this)[FuelEfficiencyViewModel::class.java]
        val tankSize  = binding.tankSize.editTextField.editText
        val average = binding.average.editTextField.editText
        val totalTravelInTank = binding.totalTank.editTextField.editText
//        binding.apply {
//            binding.tankSize.etFuelPrice.setText("dsfa")
//        }
        tankSize?.addTextChangedListener{
            if (it.toString().isNotEmpty())
            fuelEfficiencyViewModel.initialFuel = it.toString().toFloat()
        }
        average?.addTextChangedListener {
            if(it.toString().isNotEmpty())
            fuelEfficiencyViewModel.initialReading = it.toString().toFloat()
        }
        totalTravelInTank?.addTextChangedListener {
            if (it.toString().isNotEmpty())
            fuelEfficiencyViewModel.petrolPriceAtRefil = it.toString().toFloat()
        }
        binding.fuelEfficiencyBtn.setOnClickListener{
            if (tankSize!!.text.isNotBlank()&&average!!.text.isNotBlank()&& totalTravelInTank!!.text.isNotBlank()) {
                if (check){
                    sharedPref.edit().putFloat("fuelInLiters", tankSize.text.toString().toFloat()).apply()
                    sharedPref.edit().putFloat("initialReading", average.text.toString().toFloat()).apply()
                    sharedPref.edit().putFloat("fuelPrice", totalTravelInTank.text.toString().toFloat()).apply()
                    Toast.makeText(this, "Your data is Saved", Toast.LENGTH_LONG).show()
                    finish()
                }
                else{
//                  fuelEfficiencyViewModel.fuelUsed = sharedPref.getFloat("fuelInLiters",0f)
                  fuelEfficiencyViewModel.finalReading = sharedPref.getFloat("initialReading",0f)
                  fuelEfficiencyViewModel.fuelPriceAtEnd = sharedPref.getFloat("fuelPrice",0f)

                    fuelEfficiencyViewModel.efficiencyHealth()
                    fuelEfficiencyViewModel.EfficiencyInsertIntoDatabase(this)
                }
                sharedPref.edit().putBoolean("flag", !check).apply()
//                fuelEfficiencyViewModel.efficiencyHealth()
               // fuelEfficiencyViewModel.EfficiencyInsertIntoDatabase(this)
            }
            else{
                Toast.makeText(this, "Please Fill all text First", Toast.LENGTH_LONG).show()
            }
        }
        fuelEfficiencyViewModel.result.observe(this){
            binding.textViewFuelEfficiency.text = it
        }
    }
}