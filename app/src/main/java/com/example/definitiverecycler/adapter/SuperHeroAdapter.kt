package com.example.definitiverecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.definitiverecycler.R
import com.example.definitiverecycler.SuperHero

class SuperHeroAdapter(
    private val superheroList: List<SuperHero>,
    private val onClickListener: (SuperHero) -> Unit // Agregamos el parámetro onClickListener, y le decimos que recibe un SuperHero
) : RecyclerView.Adapter<SuperHeroViewHolder>() {
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
        holder.render(item, onClickListener) // Pasamos el parámetro onClickListener al método render de SuperHeroViewHolder
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }
}