package com.example.definitiverecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.definitiverecycler.R
import com.example.definitiverecycler.SuperHero
import com.example.definitiverecycler.SuperHeroDiffUtil

class SuperHeroAdapter(
    private var superheroList: List<SuperHero>,
    private val onClickListener: (SuperHero) -> Unit, // Agregamos el parámetro onClickListener, y le decimos que recibe un SuperHero
    private val onClickListenerDelete: (SuperHero) -> Unit // Retorna el item
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
        holder.render(item, onClickListener, onClickListenerDelete) // Pasamos el parámetro onClickListener al método render de SuperHeroViewHolder
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }

    fun updateList(newList:List<SuperHero>) {
        val superHeroDiff = SuperHeroDiffUtil(superheroList, newList)
        val result = DiffUtil.calculateDiff(superHeroDiff)
        superheroList = newList
        result.dispatchUpdatesTo(this)
    }

}
