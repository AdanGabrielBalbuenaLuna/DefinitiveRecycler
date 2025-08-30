package com.example.definitiverecycler

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.definitiverecycler.adapter.SuperHeroAdapter
import com.example.definitiverecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var superHereMutableList: MutableList<SuperHero> = SuperHeroProvider.superHeroList.toMutableList()
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.btnAddSuperHero.setOnClickListener { createSuperHero() }

    }

    private fun createSuperHero() {
        val superHero = SuperHero(
            "¿¿¿","???","Who knows", "https://i.ytimg.com/vi/iHkBpGSOy9o/maxresdefault.jpg"
        )
        superHereMutableList.add(index = 7, superHero) // Agregamos el item en la posicion 3
        adapter.notifyItemInserted(7) // Actualizamos el adapter con el nuevo item -> añade al final
    }
    fun initRecyclerView(){
        adapter = SuperHeroAdapter(
            superHereMutableList,
            { superHero -> onItemSelected(superHero) }, // { -> onItemSelected(it) }
            { position -> onDeletedItem(position) }
        )
        binding.recyclerSuperHero.adapter = adapter

        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerSuperHero.layoutManager = manager
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