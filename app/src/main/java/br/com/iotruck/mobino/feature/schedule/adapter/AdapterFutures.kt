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
        val itemViewToday = LayoutInflater.from(parent.context).inflate(R.layout.travels_component_today,
            parent,false)

        return MyViewHolder(itemViewToday)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvTodayTravelName : TextView = itemView.findViewById(R.id.tv_today_travel_name)
        val tvAnalystNameToday : TextView = itemView.findViewById(R.id.tv_analyst_names_today)
        val tvTravelDestinyToday : TextView = itemView.findViewById(R.id.tv_travel_destiny_today)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        val date = SimpleDateFormat("dd/M/yyyy")
        val currentDate = date.format(Date())

        if(currentItem.dateTravel.equals(currentDate)) {
            holder.tvTodayTravelName.text = currentItem.code
            holder.tvAnalystNameToday.text = currentItem.analyst.name
            holder.tvTravelDestinyToday.text = currentItem.destiny.address
        }
    }


}