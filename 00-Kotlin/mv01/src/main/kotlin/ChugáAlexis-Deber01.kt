import java.util.*
import kotlin.collections.ArrayList

var nombre="Alexis"

fun main(){

    println("**********************************************************")
    println("ALEXIS CHUGA -- DEBER 01 -- APLICACIONES MÓVILES")
    println("**********************************************************")
    println("Los equipos de futbol creados son los siguientes:")
    println("Escoja una opción: \n" +
            "1.Crear un equipo\n" +
            "2.Ver la informacion de un equipo\n" +
            "3.Actualizar un equipo\n" +
            "4.Eliminar un equipo")
    var opcion = readLine()
    when(opcion) {
        ("1") -> {
            println("escogió la opcion 'Crear un equipo'")
            println("Complete los siguientes campos: ")
            println("Ingrese el nombre del equipo: ")
            var nombre= readLine()
            println("Ingrese el precio del equipo: ")
            var precio= readLine()
            println("Ingrese el año de fundacion del equipo: ")
            var anio= readLine()
            println("Ingrese S, si el equipo es parte de la Federacion Ecuatoriana de Futbol," +
                    " o N, en caso contrario: ")
            var perteneceF= readLine()


        }
        "2" -> {
            println("escogio la opcion2")
        }
        "3" -> {
            println("escogio la opcion3")
        }
        "4" -> {
            println("escogio la opcion4")
        }

        else -> println("escogio la opcion1")
    }
}
fun crearEquipo(){

}

fun perteneceFEFfun(dato: String?): Boolean{
    var datoM= dato?.uppercase()
    if (datoM=="S"){
        return true
    }
    else {
        return false
    }
}

class EquipoFutbol(
    protected var nombreEquipo: String,
    protected var precioEquipo: Double,
    protected var anioFundacion: Int,
    protected var listaJugadores: ArrayList<Jugador>,
    protected var perteneceFEF:Boolean,
){
    init {
        nombreEquipo
        precioEquipo
        anioFundacion
        listaJugadores
        perteneceFEF
    }
    fun crearEquipo(){

    }
    fun imprimir(){
        println("nombre:$nombreEquipo")
        println("precio:$precioEquipo")
        println("Año de fundación:$anioFundacion")
        println("Lista de Jugadores:$listaJugadores.")
        for (j in  listaJugadores){
            println(j.imprimir())
        }
        println("Pertenece a la FEF:$perteneceFEF")

    }
}
class Jugador(
    protected var nombreJugador: String,
    protected var fechaNacimiento: Date,
    protected var estatura: Float,
    protected var posicion:String,
    protected var equipo: String
){
    init {
        nombreJugador
        fechaNacimiento
        estatura
        posicion
        equipo
    }
    fun imprimir(){
        println(posicion)
    }
}