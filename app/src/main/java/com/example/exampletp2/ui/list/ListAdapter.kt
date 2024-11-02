package com.example.exampletp2.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.exampletp2.R
import com.example.exampletp2.data.UserDAO
import com.example.exampletp2.data.model.User
import com.example.exampletp2.databinding.ItemRecyclerviewListBinding


class ListAdapter(val onUserClick:(user: User) -> Unit): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var userList = emptyList<User>()


    inner class ListViewHolder(private val binding: ItemRecyclerviewListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                tvName.text = user.name
                tvLastName.text = user.lastName
                tvAge.text = user.age.toString()
                tvId.text = user.id.toString()
            }

            binding.root.setOnClickListener {
                onUserClick(user)
                //val bundle = Bundle()
                //bundle.putSerializable("animal", user)
                //itemView.findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val binding = ItemRecyclerviewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        val user = userList.get(position)
        holder.bind(user = user)
    }

    override fun getItemCount(): Int = userList.size


    @SuppressLint("NotifyDataSetChanged")
    fun setList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}