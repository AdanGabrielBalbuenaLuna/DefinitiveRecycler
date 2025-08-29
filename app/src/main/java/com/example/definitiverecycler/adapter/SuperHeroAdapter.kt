package com.example.definitiverecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.definitiverecycler.R
import com.example.definitiverecycler.SuperHero

class SuperHeroAdapter(private val superheroList: List<SuperHero>): RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(
        holder: SuperHeroViewHolder,
        position: Int
    ) {
        val item = superheroList[position]
        holder.render(item) // Aquí se llama al método render de SuperHeroViewHolder
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }
}