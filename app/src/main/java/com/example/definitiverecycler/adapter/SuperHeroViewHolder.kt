package com.example.definitiverecycler.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.definitiverecycler.SuperHero
import com.example.definitiverecycler.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemSuperheroBinding.bind(view)

    fun render(superHeroModel: SuperHero) {
        binding.tvSuperHeroName.text = superHeroModel.superhero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvPublisher.text = superHeroModel.publisher
        Glide.with(binding.ivSuperHero.context).load(superHeroModel.photo).into(binding.ivSuperHero)
        binding.ivSuperHero.setOnClickListener {
            Toast.makeText(binding.ivSuperHero.context, superHeroModel.realName, Toast.LENGTH_SHORT).show()
        }

        itemView.setOnClickListener {
            Toast.makeText(
                binding.ivSuperHero.context,
                superHeroModel.superhero,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}