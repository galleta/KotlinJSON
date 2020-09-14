package com.example.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import controlador.ControladorPersonas
import modelo.ServidorPHPException
import org.json.JSONException
import vista.AdaptadorPersona

/*
    Fuentes:
        https://www.javatpoint.com/kotlin-android-json-parsing-using-url
        https://github.com/square/okhttp
        https://stackoverflow.com/questions/49394844/kotlin-how-to-read-json-string-on-url
        https://www.learn2crack.com/2013/10/android-asynctask-json-parsing-example.html
 */

/**
 * @author Francisco Jesús Delgado Almirón
 */
class MainActivity : AppCompatActivity() {

    private lateinit var listapersonas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ********** Obtengo los recursos de la actividad **********
        listapersonas = findViewById(R.id.listapersonas)
        // **********************************************************

        val controladorp = ControladorPersonas(this)

        try {
            val personas = controladorp.obtenerTodasPersonas()

            // Muestro los datos en la lista
            val adaptador = AdaptadorPersona(personas, this)
            listapersonas.layoutManager = LinearLayoutManager(this)
            listapersonas.adapter = adaptador
        }
        catch (error: ServidorPHPException)
        {
            println("Error $error")
        }
        catch(error: JSONException)
        {
            println("Error $error")
        }

    }

}