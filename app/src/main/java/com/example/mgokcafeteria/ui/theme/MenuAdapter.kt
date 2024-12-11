package com.example.mgokcafeteria.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mgokcafeteria.R
import com.example.mgokcafeteria.models.MenuItem

class MenuAdapter(private val menuItems: List<MenuItem>) : RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.dishName.text = menuItem.name
        holder.dishPrice.text = menuItem.price.toString()
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishImage: ImageView = itemView.findViewById(R.id.menuItemImage)
        val dishName: TextView = itemView.findViewById(R.id.menuItemName)
        val dishPrice: TextView = itemView.findViewById(R.id.menuItemPrice)
    }
}
