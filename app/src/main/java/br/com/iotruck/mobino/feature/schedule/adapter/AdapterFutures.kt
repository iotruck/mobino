package br.com.iotruck.mobino.feature.schedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.feature.schedule.model.Travel
import java.text.SimpleDateFormat
import java.util.*


class AdapterFutures(private val newList : MutableList<Travel>) :
    RecyclerView.Adapter<AdapterFutures.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder  {
        val itemViewFutures = LayoutInflater.from(parent.context).inflate(R.layout.travels_component_future,
            parent,false)

        return MyViewHolder(itemViewFutures)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvDateTravel : TextView = itemView.findViewById(R.id.tv_travel_date)
        val tvFutureTravelName : TextView = itemView.findViewById(R.id.tv_future_travel_name)
        val tvAnalystNameFuture : TextView = itemView.findViewById(R.id.tv_analyst_names_future)
        val tvTravelDestinyFuture : TextView = itemView.findViewById(R.id.tv_travel_destiny_future)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        val date = SimpleDateFormat("dd/M/yyyy")
        val currentDate = date.format(Date())

            holder.tvFutureTravelName.text = currentItem.code
            holder.tvAnalystNameFuture.text = currentItem.analyst.name
            holder.tvTravelDestinyFuture.text = currentItem.destiny.address
            holder.tvDateTravel.text = currentItem.dateTravel
    }


}