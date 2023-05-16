package com.example.fuelmanagmentapplication.ui.activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuelmanagmentapplication.databinding.ExampleItemBinding
import com.example.fuelmanagmentapplication.domain.model.fuelexpensecalculation
class FuelExpenseHistoryadopter(val list: List<fuelexpensecalculation>): RecyclerView.Adapter<FuelExpenseHistoryadopter.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ExampleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: FuelExpenseHistoryadopter.ViewHolder, position: Int) {
       holder.bind(list[position])
    }
    override fun getItemCount() = list.size
    inner class ViewHolder(val binding:ExampleItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(fuelexpensecalculation: fuelexpensecalculation) {
             binding.fuelPriceResult.text= fuelexpensecalculation.fuelPrice.toString()
             binding.averagePerKmResult.text=fuelexpensecalculation.VehicleAverage.toString()
             binding.totalDistanceTravelResult.text = fuelexpensecalculation.totalDistance.toString()
             binding.totalCostOfFuelResult.text= fuelexpensecalculation.totalCalculation.toString()
        }
    }
}