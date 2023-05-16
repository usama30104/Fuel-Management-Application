package com.example.fuelmanagmentapplication.ui.activity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fuelmanagmentapplication.R
import com.example.fuelmanagmentapplication.databinding.ActivityMainBinding
class Main : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // create instance of the ActivityMainBinding,
        // as we have only one layout activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        // binding.root returns the root layout,
        // which is activity_main.xml file itself
        setContentView(binding.root)
        binding.fuelEfficiency.imageView.setImageResource(R.drawable.efficency)
        binding.fuelEfficiency.tvFuelExpense.text = "Fuel Efficiency"
        binding.savingTips.imageView.setImageResource(R.drawable.idea)
        binding.savingTips.tvFuelExpense.text = "History"
        binding.fuelExpense.root.setOnClickListener {
            startActivity(Intent(this, FuelExpenseCalculation::class.java))
        }
        binding.fuelEfficiency.root.setOnClickListener{
            startActivity(Intent(this,FuelEfficiency::class.java))
        }
        binding.savingTips.root.setOnClickListener{
            startActivity(Intent(this, NavigationButtons::class.java))
            //change
        }
    }
}