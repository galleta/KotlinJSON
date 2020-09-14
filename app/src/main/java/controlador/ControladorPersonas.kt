package controlador

import android.content.Context
import com.example.json.R
import modelo.json.JSONParser
import modelo.Persona
import modelo.ServidorPHPException

/**
 * Esta clase representa el controlador de las personas
 * @author Francisco Jesús Delgado Almirón
 */
class ControladorPersonas(private val contexto: Context) {

    // Códigos de los errores posibles
    private val RESULTADO_OK = 1
    private val RESULTADO_ERROR = 2
    private val RESULTADO_ERROR_DESCONOCIDO = 3

    private var jsonparser = JSONParser(contexto)

    // URLs para obtener los datos
    private val urlservidor = "http://192.168.0.107/personas/"
    private val obtenerTodasPersonasURL = urlservidor + "obtenerTodasPersonas.php"

    /**
     * Obtiene todas las personas que se encuentran en el servidor
     * @return ArrayList de personas que contiene todas las personas que hay en el servidor
     */
    fun obtenerTodasPersonas(): ArrayList<Persona>
    {
        val personas = ArrayList<Persona>()

        // Obtengo los datos de las personas en formato JSONArray
        val datos = jsonparser.getJSONArrayFromURL(obtenerTodasPersonasURL)

        if( datos != null )
        {
            val resultadoobtenido = datos.getJSONObject(0).getInt("estado")

            when( resultadoobtenido )
            {
                RESULTADO_OK -> {
                    // Obtengo los datos del mensaje, donde están las personas
                    val mensaje = datos.getJSONObject(0).getJSONArray("mensaje")

                    for (i in 0 until mensaje.length())
                    {
                        val DNI = mensaje.getJSONObject(i).getString("DNI")
                        val nombre = mensaje.getJSONObject(i).getString("nombre")
                        val apellidos = mensaje.getJSONObject(i).getString("apellidos")
                        val telefono = mensaje.getJSONObject(i).getString("telefono")
                        val email = mensaje.getJSONObject(i).getString("email")
                        val elemento = Persona (DNI, nombre, apellidos, telefono, email)
                        personas.add(elemento)
                    }
                }
                RESULTADO_ERROR -> {
                    throw ServidorPHPException(contexto.getString(R.string.textoErrorDatosIncorrectos))
                }
                RESULTADO_ERROR_DESCONOCIDO -> {
                    throw ServidorPHPException(contexto.getString(R.string.textoErrorObtenerDatosServidor))
                }
            }
        }
        else
        {
            throw ServidorPHPException(contexto.getString(R.string.textoErrorObtenerDatosServidor))
        }

        return personas
    }
}