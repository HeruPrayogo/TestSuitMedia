package com.test.Suitmedia.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.Suitmedia.R
import com.test.Suitmedia.databinding.ItemUserBinding
import com.test.Suitmedia.model.Data
import com.test.Suitmedia.model.UserData

class UserAdapter(var listUser: List<Data>,
                  var onItemClick : ((Data) -> Unit)): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.Email.text = listUser[position].email
        holder.binding.FirstName.text = listUser[position].firstName
        holder.binding.LastName.text = listUser[position].lastName
        Glide.with(holder.itemView).load(listUser[position].avatar).into(holder.binding.ProfilePicture)
        holder.binding.card.setOnClickListener {
            onItemClick(listUser[position])
        }

    }

    override fun getItemCount(): Int {
        return  listUser.size
    }
}