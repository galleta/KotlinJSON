package modelo.json

import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.json.R
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.SocketTimeoutException

/**
 * Esta clase crea una conexión HTTP a una URL. Utiliza la biblioteca com.squareup.okhttp3
 * @author Francisco Jesús Delgado Almirón
 */
class JSONAsyncTask(private val context: Context) : AsyncTask<String, String, String>()
{
    // Variables privadas de clase
    private val clientehttp = OkHttpClient()

    // Este método se ejecuta en segundo plano
    override fun doInBackground(vararg datos: String?): String
    {
        // Obtenemos la URL para hacer la petición
        val direccion = datos[0]!!

        // Creamos la petición
        val peticion = Request.Builder()
            .url(direccion)
            .build()

        try {
            // Ejecutamos la petición
            // El método doInBackground devuelve el String de la petición
            clientehttp.newCall(peticion).execute().use { response -> return response.body!!.string() }
        }
        catch (error: SocketTimeoutException)
        {
            // Si no se puede conectar con el servidor se muestra un error con un Toast
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, R.string.textoErrorImposibleConectar, Toast.LENGTH_LONG).show()
            }
            return ""
        }
    }

    // Lo que llega a onPostExecute es lo que se devuelve en doInBackground
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}