package com.example.definitiverecycler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.definitiverecycler.adapter.SuperHeroAdapter
import com.example.definitiverecycler.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var superHereMutableList: List<SuperHero> = SuperHeroProvider.superHeroList
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
        configSwipe()

    }

    private fun configSwipe() {
        binding.swipe.setColorSchemeResources(R.color.red, R.color.orange)
        binding.swipe.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.black))

        binding.swipe.setOnRefreshListener {
            //Log.i("Gabo", "Mensaje")
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipe.isRefreshing = false
                initRecyclerView() // Simulacion de llamada al recylcer puede ser una llamada a internet
            }, 2000)
            //Thread.sleep(2000) // no debe usarse por que pausa el hilo, que es donde se ejecuta la instuccion anterior
        }
    }

    private fun configFilter() {
        binding.etFilter.addTextChangedListener { textoAFiltrar ->
            val superHeroFiltered = superHereMutableList.filter { apodoTemporal ->
                apodoTemporal.superhero.lowercase().contains(textoAFiltrar.toString().lowercase())
            }

            //Log.i("Gabo", it.toString())  // Muestra como funciona addTextChangedListener
            adapter.updateList(superHeroFiltered)
        }
    }

    private fun createSuperHero() {
        val randomId = Random.nextInt(1000)
        val superHero = SuperHero(
            "$randomId","???","Who knows $randomId", "https://i.ytimg.com/vi/iHkBpGSOy9o/maxresdefault.jpg"
        )
        superHereMutableList = superHereMutableList.plus(superHero)
        adapter.updateList(superHereMutableList)
        linearLayoutManager.scrollToPositionWithOffset(superHereMutableList.size-1, 10) // Scroll a la posicion "ultima" con un margen de 10
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

    fun onDeletedItem(superHero: SuperHero) {
        superHereMutableList = superHereMutableList.minus(superHero) // Eliminamos el item
        adapter.updateList(superHereMutableList)
    }

    fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(this, superHero.superhero, Toast.LENGTH_SHORT).show()
    }

}
