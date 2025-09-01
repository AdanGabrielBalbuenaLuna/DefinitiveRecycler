package com.example.definitiverecycler

import androidx.recyclerview.widget.DiffUtil

class SuperHeroDiffUtil(
    private val oldList: List<SuperHero>,
    private val newList: List<SuperHero>
): DiffUtil.Callback()  {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition].superhero == newList[newItemPosition].superhero
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
