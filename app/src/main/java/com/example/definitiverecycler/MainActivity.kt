package com.example.definitiverecycler

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.definitiverecycler.adapter.SuperHeroAdapter
import com.example.definitiverecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var superHereMutableList: MutableList<SuperHero> = SuperHeroProvider.superHeroList.toMutableList()
    private lateinit var adapter: SuperHeroAdapter

    private val linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.btnAddSuperHero.setOnClickListener { createSuperHero() }
        configFilter()

    }

    private fun configFilter() {
        binding.etFilter.addTextChangedListener { textoAFiltrar ->
            val superHeroFiltered = superHereMutableList.filter { apodoTemporal ->
                apodoTemporal.superhero.lowercase().contains(textoAFiltrar.toString().lowercase())
            }

            //Log.i("Gabo", it.toString())  // Muestra como funciona addTextChangedListener
            adapter.updateSuperHeroes(superHeroFiltered)
        }
    }


    private fun createSuperHero() {
        val superHero = SuperHero(
            "¿¿¿","???","Who knows", "https://i.ytimg.com/vi/iHkBpGSOy9o/maxresdefault.jpg"
        )
        superHereMutableList.add(index = 7, superHero) // Agregamos el item en la posicion 3
        adapter.notifyItemInserted(7) // Actualizamos el adapter con el nuevo item -> añade al final
        linearLayoutManager.scrollToPositionWithOffset(7, 10) // Scroll a la posicion 7 con un margen de 10
    }
    fun initRecyclerView(){
        adapter = SuperHeroAdapter(
            superHereMutableList,
            { superHero -> onItemSelected(superHero) }, // { -> onItemSelected(it) }
            { position -> onDeletedItem(position) }
        )
        binding.recyclerSuperHero.adapter = adapter

        val decoration = DividerItemDecoration(this, linearLayoutManager.orientation)
        binding.recyclerSuperHero.layoutManager = linearLayoutManager
        binding.recyclerSuperHero.addItemDecoration(decoration)
    }

    fun onDeletedItem(position: Int) {
        superHereMutableList.removeAt(position) // Eliminamos el item
        adapter.notifyItemRemoved(position) // Actualizamos el adapter
    }

    fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(this, superHero.superhero, Toast.LENGTH_SHORT).show()
    }
}