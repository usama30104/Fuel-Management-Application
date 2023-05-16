package com.example.fuelmanagmentapplication.ui.activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fuelmanagmentapplication.data.FuelManagementDatabase
import com.example.fuelmanagmentapplication.databinding.ActivityFuelEfficiencyHistoryBinding
import kotlinx.coroutines.launch
class FuelEfficiencyHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFuelEfficiencyHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFuelEfficiencyHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            FuelManagementDatabase.invoke(this@FuelEfficiencyHistoryActivity).daoefficiecny.getListOfFuelEfficiency().collect(){
                val initalizeadopter = FuelEfficicencyHistoryAdopter(it)
                binding.recycleFuelEfficiency.adapter = initalizeadopter
            }
        }
    }
}