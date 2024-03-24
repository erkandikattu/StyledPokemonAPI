package com.example.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var typeName = ""
    var damageClass = ""
    var examplePokemon = ""
    var increment = 1
    private fun getPokemonType() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/type/$increment", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("PokemonType", "response successful$json")
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
        val pokemonType = findViewById<TextView>(R.id.typeNameText)
        val typeDamageClass = findViewById<TextView>(R.id.damageClassText)
        val typeExamplePokemon = findViewById<TextView>(R.id.exPokemonText)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener() {
            pokemonType.text = typeName
            typeDamageClass.text = damageClass
            typeExamplePokemon.text = examplePokemon
            getPokemonType()
        }
    }
}