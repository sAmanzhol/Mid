package com.example.mid.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mid.R
import com.example.mid.model.Dragon
import com.squareup.picasso.Picasso
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
            Picasso.get().load(dragon.flickrImages?.get(0)).into(view.movie_poster_view)
            view.title.text = "Name: " + dragon.name
            view.priority.text = "Type: " + dragon.type
            view.content.text = dragon.description
        }
    }
}