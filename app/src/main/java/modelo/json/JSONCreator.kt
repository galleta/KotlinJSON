package modelo.json

import org.json.JSONArray
import org.json.JSONObject

// Fuente: https://stackoverflow.com/questions/44295665/how-to-create-a-jsonobject-from-string-in-kotlin/44297201

/*
    Ejemplo de uso para crear un JSONObject:

    var array = JSONCreator(
        "name" to "Elvis Presley",
        "furtherDetails" to JSONCreator(
            "GreatestHits" to arrayOf("Surrender", "Jailhouse rock"),
            "Genre" to "Rock",
            "Died" to 1977)
    )
*/

/**
 * Clase que permite crear un JSON
 * @author Francisco Jesús Delgado Almirón
 */
class JSONCreator {
    private var json = JSONObject()

    /**
     * Constructor
     * @param valores Elementos para agregar al JSON
     */
    constructor(vararg valores: Pair<String, *>)  {
        add(*valores)
    }

    /**
     * Agrega un array de elementos al JSON
     * @param valores Elementos a agregar al JSON
     */
    fun add(vararg valores: Pair<String, *>) {
        for ((key, value) in valores) {
            when (value) {
                is Boolean -> json.put(key, value)
                is Number -> add(key, value)
                is String -> json.put(key, value)
                is JSONCreator -> json.put(key, value.json)
                is Array<*> -> add(key, value)
                is JSONObject -> json.put(key, value)
                is JSONArray -> json.put(key, value)
                else -> json.put(key, null) // Or whatever, on illegal input
            }
        }
    }

    /**
     * Agrega un elemento al JSON
     * @param key Clave del elemento
     * @param value Valor del elemento
     * @return Elemento JSONCreator actualizado
     */
    fun add(key: String, value: Number): JSONCreator {
        when (value) {
            is Int -> json.put(key, value)
            is Long -> json.put(key, value)
            is Float -> json.put(key, value)
            is Double -> json.put(key, value)
            else -> {} // Do what you do on error
        }

        return this
    }

    /**
     * Agrega un elemento array al JSON
     * @param key Clave del elemento
     * @param items Array de valores del elemento
     * return Elemento JSONCreator actualizado
     */
    fun <T> add(key: String, items: Array<T>): JSONCreator {
        val jsonArray = JSONArray()
        items.forEach {
            when (it) {
                is String,is Long,is Int, is Boolean -> jsonArray.put(it)
                is JSONCreator -> jsonArray.put(it.json)
                else -> try {jsonArray.put(it)} catch (ignored:Exception) {/*handle the error*/}
            }
        }

        json.put(key, jsonArray)

        return this
    }

    /**
     * Convierte el elemento a JSONObjecto
     * @return JSONObject equivalente
     */
    fun toJSONObject() = json

    /**
     * Convierte el elemento a String
     * @return String equivalente
     */
    override fun toString() = json.toString()
}