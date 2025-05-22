package com.sarrawi.mycolor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarrawi.mycolor.databinding.ItemlayoutBinding

class Adapter(
    private var items: List<String>,
    private var selectedColor: Int
) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemlayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.textView.text = item

        // تغيير الخلفية واللون
        holder.binding.cardview.setCardBackgroundColor(selectedColor)
        holder.binding.textView.setTextColor(getContrastingTextColor(selectedColor))
    }

    override fun getItemCount() = items.size

    fun updateColor(color: Int) {
        selectedColor = color
        notifyDataSetChanged()
    }
}
