package com.example.crudexampleusingroom.adapter

import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crudexampleusingroom.R
import com.example.crudexampleusingroom.databinding.ItemBinding
import com.example.crudexampleusingroom.db.Subscribers

class MyAdapter(private val subscribersList:List<Subscribers>,val listener:ClickListener) : RecyclerView.Adapter<MyViewHolder>() {

    interface ClickListener{
        fun onRowClick(subscribers: Subscribers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val binding:ItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.layout_item,parent,false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList.get(position))
        holder.binding.llItem.setOnClickListener(View.OnClickListener {
            listener.onRowClick(subscribersList.get(holder.adapterPosition))
        })
        
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }
}

class MyViewHolder(binding:ItemBinding):RecyclerView.ViewHolder(binding.root){
    val binding = binding
    fun bind(subscribers: Subscribers){
        binding.txtName.text = subscribers.subscriber_name
        binding.txtEmail.text = subscribers.subscriber_email
    }


}