package vista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.json.R
import kotlinx.android.synthetic.main.lista_persona.view.*
import modelo.Persona

/**
 * Esta clase implementa un adaptador para mostrar las personas en una lista
 * @author Francisco Jesús Delgado Almirón
 */
class AdaptadorPersona(private val personas : ArrayList<Persona>, private val context: Context) : RecyclerView.Adapter<AdaptadorPersona.HolderPersona>()
{
    /**
     * Clase interna para implementar el Holder de las personas
     */
    inner class HolderPersona (view: View) : RecyclerView.ViewHolder(view)
    {
        // Obtenemos todos los recursos de la vista donde se verá cada persona
        val tDNI: TextView = view.tDNI
        val tNombre: TextView = view.tNombre
        val tApellidos: TextView = view.tApellidos
        val tTelefono: TextView = view.tTelefono
        val tEmail: TextView = view.tEmail
    }

    /**
     * Carga la vista donde será mostrada cada persona en la lista
     */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderPersona
    {
        return HolderPersona(LayoutInflater.from(context).inflate(R.layout.lista_persona, p0, false))
    }

    /**
     * Devuelve la cantidad de personas que habrá en la lista
     */
    override fun getItemCount(): Int
    {
        return personas.size
    }

    /**
     * Muestra la información de cada persona
     */
    override fun onBindViewHolder(personadeturno: HolderPersona, posicion: Int)
    {
        personadeturno.tDNI.text = personas[posicion].getDNI()
        personadeturno.tNombre.text = personas[posicion].getNombre()
        personadeturno.tApellidos.text = personas[posicion].getApellidos()
        personadeturno.tTelefono.text = personas[posicion].getTelefono()
        personadeturno.tEmail.text = personas[posicion].getEmail()
    }
}