package modelo

/**
 * Esta clase representa una persona
 * @author Francisco Jesús Delgado Almirón
 */
class Persona(private var DNI: String, private var nombre: String, private var apellidos: String, private var telefono: String, private var email: String) {

    fun getDNI(): String
    {
        return DNI
    }

    fun getNombre(): String
    {
        return nombre
    }

    fun getApellidos(): String
    {
        return apellidos
    }

    fun getTelefono(): String
    {
        return telefono
    }

    fun getEmail(): String
    {
        return email
    }
}