package com.e.jakobfe_oblig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlinx.coroutines.runBlocking as runBlocking

class MainActivity : AppCompatActivity() {

    private val tag= "MainActivity" // for logging purposes
    private val baseUrl= "https://data.uio.no/studies/v1/course/IN2000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        runBlocking {
            try {
                val response = Fuel.get(baseUrl).awaitString()
                Log.d(tag, response)
                val course = gson.fromJson(response, Array<Alpakka>::class.java).toMutableList()
                Log.d(tag, course.toString())
                textView.text = course.get(0).name.toString()
            }
            catch (e  : Exception) {
                // Log.e(tag, e.message)
                Toast.makeText(this@MainActivity, "could not find course", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


data class Alpakka(val name: String?, val age: String?, val location: String?, val imgSrc: String?)

data class Metadata(val count: Number?, val total: Number?, val offset: Number?, val status: String?, val limit: Number?)