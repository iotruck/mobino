package br.com.iotruck.mobino.feature.schedule.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.feature.schedule.model.Travel

class Adapter(private val newList : List<Travel>) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.travels_component,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.tvTodayTravelName.text = currentItem.code
        holder.tvAnalystNameToday.text = currentItem.analyst.name
        holder.tvTravelDestinyToday.text = currentItem.destiny.address
    }

    override fun getItemCount(): Int {
       return newList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val tvTodayTravelName : TextView = itemView.findViewById(R.id.tv_today_travel_name)
        val tvAnalystNameToday : TextView = itemView.findViewById(R.id.tv_analyst_names_today)
        val tvTravelDestinyToday : TextView = itemView.findViewById(R.id.tv_travel_destiny_today)

    }
}