package br.com.iotruck.mobino.feature.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.model.Message


class AdapterMessage(private val newList: MutableList<Message>) :
    RecyclerView.Adapter<AdapterMessage.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewFutures = LayoutInflater.from(parent.context).inflate(
            R.layout.message_content_component,
            parent, false
        )

        return MyViewHolder(itemViewFutures)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRemetente: TextView = itemView.findViewById(R.id.tv_remetente)
        val tvContentMessage: TextView = itemView.findViewById(R.id.tv_content_message)
        val tvDateMessage: TextView = itemView.findViewById(R.id.tv_date_message)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.tvRemetente.text = currentItem.sender
        holder.tvContentMessage.text = currentItem.content
        holder.tvDateMessage.text = currentItem.dateTimeMessage.toString()
    }

}