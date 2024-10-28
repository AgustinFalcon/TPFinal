package com.example.exampletp2.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.exampletp2.R
import com.example.exampletp2.databinding.ItemRecyclerviewListBinding


class ListAdapter(private val list: List<String>): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRecyclerviewListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(animal: String) {
            binding.tvAnimal.text = animal

            binding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("animal", animal)
                itemView.findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val binding = ItemRecyclerviewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        val animal = list.get(position)
        holder.bind(animal = animal)
    }

    override fun getItemCount(): Int = list.size
}