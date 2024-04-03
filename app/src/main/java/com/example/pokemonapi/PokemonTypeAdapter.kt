package com.example.pokemonapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonTypeAdapter(private val pokemonList: List<String>, private val typeList: List<String>, private val generationList: List<String>) : RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokemonName: TextView = view.findViewById(R.id.pokemon_list)
        val typeName: TextView = view.findViewById(R.id.type_list)
        val generationName: TextView = view.findViewById(R.id.generation_list)
        //val damageClass: TextView
        //val examplePokemon: TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.pokemonName.text = pokemon
        val type = typeList[position]
        holder.typeName.text = type
        val generation = generationList[position]
        holder.generationName.text = generation
    }
}