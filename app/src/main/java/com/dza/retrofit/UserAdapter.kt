package com.dza.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dza.retrofit.databinding.ItemUserBinding
import com.dza.retrofit.model.Data

class UserAdapter(
    private val userList: List<Data>,
    private val onItemClick: (Data) -> Unit
) : RecyclerView.Adapter<UserAdapter.ItemUserViewHolder>() {

    inner class ItemUserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Data) {
            with(binding) {
                userName.text = "${user.firstName} ${user.lastName}"
                userEmail.text = user.email
                Glide.with(userImg.context).load(user.avatar).into(userImg)
                root.setOnClickListener {
                    onItemClick(user)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemUserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size
}