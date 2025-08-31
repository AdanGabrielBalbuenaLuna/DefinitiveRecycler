package com.example.definitiverecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.definitiverecycler.R
import com.example.definitiverecycler.SuperHero

class SuperHeroAdapter(
    private var superheroList: List<SuperHero>,
    private val onClickListener: (SuperHero) -> Unit, // Agregamos el parámetro onClickListener, y le decimos que recibe un SuperHero
    private val onClickListenerDelete: (Int) -> Unit // Retorna la posicion del item pulsado
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

    fun updateSuperHeroes(superheroList: List<SuperHero>) {
        this.superheroList = superheroList // Indica que el cambio se hace sobre la lista de la clase como atributo
        notifyDataSetChanged() // Es posible mejorarlas con Diffutil y ListAdapter
    }
}