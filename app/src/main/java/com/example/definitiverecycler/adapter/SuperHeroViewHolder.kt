package com.example.definitiverecycler.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.definitiverecycler.R
import com.example.definitiverecycler.SuperHero

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val superHero = view.findViewById<TextView>(R.id.tvSuperHeroName)
    val realName = view.findViewById<TextView>(R.id.tvRealName)
    val publisher = view.findViewById<TextView>(R.id.tvPublisher)
    val photo = view.findViewById<ImageView>(R.id.ivSuperHero)

    fun render(superHeroModel: SuperHero) {
        superHero.text = superHeroModel.superhero
        realName.text = superHeroModel.realName
        publisher.text = superHeroModel.publisher
        photo.setImageResource(R.drawable.ic_launcher_foreground)
    }
}