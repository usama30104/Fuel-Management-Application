package com.example.fuelmanagmentapplication.ui.activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuelmanagmentapplication.databinding.FuelefficiencyItemsBinding
import com.example.fuelmanagmentapplication.domain.model.FuelEfficiencyCalculation
class FuelEfficicencyHistoryAdopter(val list:List<FuelEfficiencyCalculation>):RecyclerView.Adapter<FuelEfficicencyHistoryAdopter.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(FuelefficiencyItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount(): Int {
       return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindefficiency(list[position])
    }
    inner class ViewHolder(val binding: FuelefficiencyItemsBinding) :RecyclerView.ViewHolder(binding.root){
        fun bindefficiency(fuelEfficiencyCalculation: FuelEfficiencyCalculation){
            binding.fuelTankSizeResult.text = fuelEfficiencyCalculation.initialFuel.toString()
            binding.averagePerKmResult.text = fuelEfficiencyCalculation.initialReading.toString()
            binding.totalTravelInTankResult.text = fuelEfficiencyCalculation.petrolPriceAtRefil.toString()
            binding.calculateFuelEfficiencyResult.text = fuelEfficiencyCalculation.result
            binding.usedFuelResult.text = fuelEfficiencyCalculation.fuelUsed.toString()
            binding.finalReadingResult.text = fuelEfficiencyCalculation.finalReading.toString()
            binding.finalPriceAtEndResult.text = fuelEfficiencyCalculation.fuelPriceAtEnd.toString()
            binding.fuelDifferenceResult.text =  fuelEfficiencyCalculation.fuelDifference.toString()
        }
    }
}