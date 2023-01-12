package com.example.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val item : ArrayList<String>)
    :RecyclerView.Adapter<HistoryAdapter.viewHolder>() {

    class viewHolder(binding : ItemHistoryRowBinding) :RecyclerView.ViewHolder(binding.root){
        val llItemHistoryRow = binding.llItemHistoryRow
        val tvNumber = binding.tvNumber
        val tvDate = binding.tvDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val date : String = item[position]
        holder.tvNumber.text = (position + 1).toString()
        holder.tvDate.text = date

        if(position % 2 ==0){
            holder.llItemHistoryRow.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.lightGray))
        }else{
            holder.llItemHistoryRow.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}