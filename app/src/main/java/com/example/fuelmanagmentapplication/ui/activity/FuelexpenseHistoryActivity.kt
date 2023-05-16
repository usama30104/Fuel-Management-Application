package com.example.fuelmanagmentapplication.ui.activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fuelmanagmentapplication.data.FuelManagementDatabase
import com.example.fuelmanagmentapplication.databinding.ActivitySavingTipsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
class FuelexpenseHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavingTipsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavingTipsBinding.inflate(layoutInflater)
        // binding.root returns the root layout,
        // which is activity_main.xml file itself
        setContentView(binding.root)
        lifecycleScope.launch {
            FuelManagementDatabase.invoke(this@FuelexpenseHistoryActivity).dao.getListOfFuelExpense()
                .collectLatest {
                    val a = FuelExpenseHistoryadopter(it)
                    binding.recycleView.adapter = a
                }
        }
    }
}