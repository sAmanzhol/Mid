package com.example.mid.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mid.R
import com.example.mid.model.Dragon
import kotlinx.android.synthetic.main.item_todo.view.*

class DragonAdapter(private val dragonList: List<Dragon>)
    : RecyclerView.Adapter<DragonAdapter.DragonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DragonViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false))

    override fun onBindViewHolder(holder: DragonViewHolder, position: Int) {
        holder.bindDragon(dragonList[position])
    }

    override fun getItemCount() = dragonList.size

    inner class DragonViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindDragon(dragon: Dragon) {
            view.title.text = dragon.name
            view.priority.text = dragon.type
            view.content.text = dragon.description
        }
    }
}