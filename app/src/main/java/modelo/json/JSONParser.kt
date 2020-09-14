package modelo.json

import android.content.Context
import android.net.Uri
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.ExecutionException

/**
 * Esta clase obtiene datos JSON de una URL mediante HTTP
 * @author Francisco Jesús Delgado Almirón
 */
class JSONParser(private val contexto: Context)
{

    /**
     * Obtiene un JSONObject de una URL
     * @param URL URL para obtener los datos
     * @param parametros Parámetros que se le pasan a la URL. Este es un parámetro opcional
     * @return JSONObject que devuelve la URL
     */
    @Throws(IOException::class, ExecutionException::class, InterruptedException::class, JSONException::class)
    fun getJSONObjectFromURL(URL: String, parametros: MutableMap<String, String> = mutableMapOf()): JSONObject
    {
        val parser = JSONAsyncTask(contexto)

        // Obtengo la URL formateada con sus parámetros
        val URLfinal = formatearURL(URL, parametros)

        // Se ejecuta la petición y aquí llega lo que devuelve el método doInBackground
        var datosobtenidos = parser.execute(URLfinal).get()

        return JSONObject(datosobtenidos)
    }

    /**
     * Obtiene un JSONArray de una URL
     * @param URL URL para obtener los datos
     * @param parametros Parámetros que se le pasan a la URL. Este es un parámetro opcional
     * @return JSONArray que devuelve la URL
     */
    @Throws(IOException::class, ExecutionException::class, InterruptedException::class, JSONException::class)
    fun getJSONArrayFromURL(URL: String, parametros: MutableMap<String, String> = mutableMapOf()): JSONArray
    {
        val parser = JSONAsyncTask(contexto)

        // Obtengo la URL formateada con sus parámetros
        val URLfinal = formatearURL(URL, parametros)

        // Se ejecuta la petición y aquí llega lo que devuelve el método doInBackground
        var datosobtenidos = parser.execute(URLfinal).get()

        return JSONArray(datosobtenidos)
    }

    /**
     * Crea una URL con sus parámetros de forma correcta
     * @param URL URL base
     * @param parametros Todos los parámetros de la URL
     * @return Devuelve la URL final ya formateada con todos sus parámetros
     */
    private fun formatearURL(URL: String, parametros: MutableMap<String, String>): String
    {
        val builder: Uri.Builder = Uri.parse(URL).buildUpon()

        for ((key, value) in parametros.entries)
        {
            builder.appendQueryParameter(key, value)
        }

        return builder.build().toString()
    }
}