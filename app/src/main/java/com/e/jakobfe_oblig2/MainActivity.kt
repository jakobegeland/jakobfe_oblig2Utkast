package com.e.jakobfe_oblig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val tag= "MainActivity" // for logging purposes
    private val baseUrl= "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v20/obligatoriske-oppgaver/alpakka20.json" // to be reused in other queries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        runBlocking {
            try {
                val kall = Fuel.get(baseUrl).awaitString()
                Log.d(tag, kall)
                val alpakkaListe = gson.fromJson(kall, Array<Alpaca>::class.java).toMutableList()
                val lama1 = alpakkaListe[0]
                textView.text = "${lama1.name} kommer fra ${lama1.location} og er ${lama1.age} år."
            }
            catch (e  : Exception) {
                Log.e(tag, e.message)
                Toast.makeText(this@MainActivity, "Ingen lamaer tilgjengelig", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

