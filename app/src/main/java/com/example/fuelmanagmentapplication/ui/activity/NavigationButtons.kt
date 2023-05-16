package com.example.fuelmanagmentapplication.ui.activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fuelmanagmentapplication.databinding.ActivityNavigationButtonsBinding
class NavigationButtons : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationButtonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Button1.setOnClickListener {
            startActivity(Intent(this, FuelexpenseHistoryActivity::class.java))
        }
        binding.Button2.setOnClickListener {
            startActivity(Intent(this, FuelEfficiencyHistoryActivity::class.java))
        }
    }
}