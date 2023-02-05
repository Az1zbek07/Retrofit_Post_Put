package com.example.retrofitpostputget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitpostputget.databinding.ItemLayoutBinding
import com.example.retrofitpostputget.model.Food

class FoodAdapter: ListAdapter<Food, FoodAdapter.VHolder>(DiffCallback()) {
    private class DiffCallback: DiffUtil.ItemCallback<Food>(){
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.foodId == newItem.foodId
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            binding.textView.text = food.title
            with(binding){
                Glide.with(imageView).load(food.image).into(imageView)
            }
        }
    }
}