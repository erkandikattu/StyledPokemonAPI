package com.example.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var pokemonList: MutableList<String>
    private lateinit var typeList: MutableList<String>
    private lateinit var generationList: MutableList<String>
    private lateinit var rvPokemon: RecyclerView
    /*var typeName = ""
    var damageClass = ""
    var examplePokemon = ""
     */
    var increment = 1
    private fun getPokemonType() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/type/$increment", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("PokemonType", "response successful$json")
                val examplePokemonArray = json.jsonObject.getJSONArray("pokemon")
                /*pokemonList.clear()
                typeList.clear()
                generationList.clear()
                 */
                for(i in 0 until examplePokemonArray.length()) {
                    pokemonList.add(examplePokemonArray.getJSONObject(i).getJSONObject("pokemon").getString("name"))
                    typeList.add(json.jsonObject.getString("name"))
                    generationList.add(json.jsonObject.getJSONObject("generation").getString("name"))
                }
                val adapter = PokemonTypeAdapter(pokemonList, typeList, generationList)
                rvPokemon.adapter = adapter
                rvPokemon.layoutManager = LinearLayoutManager(this@MainActivity)
                /*
                typeName = json.jsonObject.getString("name")
                Log.d("pokemonTypeName", typeName)
                if(typeName == "fairy")
                {
                    damageClass = "null"
                }
                else {
                    val damageClassObject = json.jsonObject.getJSONObject("move_damage_class")
                    damageClass = damageClassObject.getString("name")
                }
                Log.d("pokemonDamageClass", damageClass)
                val exPokemonArray = json.jsonObject.getJSONArray("pokemon")
                val exPokemonObject = exPokemonArray.getJSONObject(0)
                examplePokemon = exPokemonObject.getJSONObject("pokemon").getString("name")
                Log.d("examplePokemon", examplePokemon)
                if(increment >= 18)
                {
                    increment = 1
                }
                else
                {
                    increment++
                }
                 */
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon Error", errorResponse)
            }
        }]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPokemonType()
        /*val pokemonType = findViewById<TextView>(R.id.typeNameText)
        val typeDamageClass = findViewById<TextView>(R.id.damageClassText)
        val typeExamplePokemon = findViewById<TextView>(R.id.exPokemonText)
        val button = findViewById<Button>(R.id.button)
         */
        rvPokemon = findViewById(R.id.pokemon_list)
        pokemonList = mutableListOf()
        typeList = mutableListOf()
        generationList = mutableListOf()
        rvPokemon.adapter = PokemonTypeAdapter(pokemonList, typeList, generationList)
        rvPokemon.layoutManager = LinearLayoutManager(this)
        getPokemonType()
        /*button.setOnClickListener() {
            pokemonType.text = typeName
            typeDamageClass.text = damageClass
            typeExamplePokemon.text = examplePokemon
            getPokemonType()
        }
         */
    }
}