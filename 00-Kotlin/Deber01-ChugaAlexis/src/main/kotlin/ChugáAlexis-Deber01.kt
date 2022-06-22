import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun main(){
    println("**********************************************************")
    println("ALEXIS CHUGA -- DEBER 01 -- APLICACIONES MOVILES")
    println("**********************************************************")
    println("Escoja al menu que desea ingresar:\n1. Menu de equipos \n2. Menu de jugadores\n3. Salir\n" +
            "Ingrese simplemente el numero de la opcion que desea (Ej: 1)")
    var opcion1= readLine()
    while(opcion1!="3"){
    when (opcion1){
        ("1")->{
            menuEquipo()
        }
        ("2")->{
           menuJugador()
        }

    }
        println("Escoja al menu que desea ingresar:\n 1. Menu de equipos \n2. Menu de jugadores\n3. Salir")
        opcion1= readLine()
    }}
fun menuEquipo(){
    println("Los equipos de futbol creados son los siguientes:")
    println("Escoja una opcion: \n" +
            "1.Crear un equipo\n" +
            "2.Ver la informacion de un equipo\n" +
            "3.Actualizar un equipo\n" +
            "4.Eliminar un equipo\n" +
            "5. Atras\n" +
            "Ingrese simplemente el numero de la opcion que desea (Ej: 1)")

    var opcion = readLine()
    while(opcion!="5"){
    when(opcion) {
        ("1") -> {
            println("Escogio la opcion 'Crear un equipo'")
            println("Complete los siguientes campos para crear el quipo: ")
            println("Ingrese el nombre del equipo: ")
            var nombre= readLine().toString()
            println("Ingrese el precio del equipo: ")
            var precio= readLine()?.toDouble()
            println("Ingrese el anio de fundacion del equipo: ")
            var anio= readLine()?.toInt()
            println("Ingrese S, si el equipo es parte de la Federacion Ecuatoriana de Futbol," +
                    " o N, en caso contrario: ")
            var perteneceF= readLine().toString()
            var perteneceFV=perteneceFEFfun(perteneceF)
            println("Ingrese el numero de victorias del equipo: ")
            var numV= readLine()?.toInt()
            println()
            EquipoFutbol(nombre,precio,anio,perteneceFV,numV)
            println("***Se ha creado el equipo ${nombre} con exito")
        }
        ("2") -> {
            println("Escogio la opcion de ver la informacion de un equipo")
            println("Los equipos creados son los siguientes:")
            listarEquipos()
            println("Ingrese el nombre del equipo del cual desea visualizar su informacion(Ej:Equipo-Quito)")
            var nombre= readLine().toString()
            leerArchivo(nombre,"Equipos")
            println("Escoja una de las siguientes opciones:\n" +
                    "1.Ver la informacion de otro equipo\n" +
                    "2.Regresar al menu de equipos")
            var opcion2= readLine().toString()
            while(opcion2!="2"){
                println("Los equipos creados son los siguientes:")
                listarEquipos()
                println("Ingrese el nombre del equipo del cual desea visualizar su informacion(Ej:Equipo-Quito)")
                var nombre= readLine().toString()
                leerArchivo(nombre,"Equipos")
                println("\nEscoja una de las siguientes opciones:\n" +
                        "1.Ver la informacion de otro equipo\n" +
                        "2.Regresar al menu de equipos")
                opcion2= readLine().toString()
            }
        }
        ("3") -> {
            println("Escogio la opcion de actualizar un equipo")
            println("Los equipos creados son los siguientes:")
            listarEquipos()
            println("Ingrese el nombre del equipo del cual desea actualizarr su informacion(Ej:Equipo-Quito)")
            var nombre= readLine().toString()
            println("El ${nombre} tiene la siguiente información")
            leerArchivo(nombre,"Equipos")
            var lista:ArrayList<String>
            lista=leerPorLineas("${nombre}","Equipos")
            println("Ingrese los nuevos datos, si no desea cambiar la información simplemente presione Enter y continúe:")
            //println("Ingrese el nombre del equipo: ")
            //var nombre2= readLine().toString()
            //if(nombre2==""){nombre2=lista[0]}
            println("Ingrese el precio del equipo: ")
            var precio2= readLine()?.toString()
            var precio:Double?
           if(precio2==""){precio= lista[1].toDouble()
           }
            else{precio= precio2?.toDouble()
           }
            println("Ingrese el anio de fundacion del equipo: ")
            var anio2= readLine()?.toString()
            var anio:Int
            if(anio2==""){anio= lista[2].toInt() }
            else{anio= anio2?.toInt()!!
            }
            println("Ingrese S, si el equipo es parte de la Federacion Ecuatoriana de Futbol," +
                    " o N, en caso contrario: ")
            var perteneceF=""
            var perteneceF2= readLine().toString()
            if(perteneceF2==""){perteneceF2= lista[3] }
            else{perteneceF= perteneceF2
            }
            var perteneceFV=perteneceFEFfun(perteneceF)
            println("Ingrese el numero de victorias del equipo: ")
            var numV2= readLine()?.toString()
            var numV:Int=0
            if(numV2==""){numV= lista[4].toInt() }
            else{numV= numV2?.toInt()!!
            }
            EquipoFutbol(nombre.substring(7),precio,anio,perteneceFV,numV)
            println("***Se ha actualizado el equipo ${nombre} con exito")
        }
        ("4") -> {
            println("Escogio la opcion de eliminar un equipo")
            println("Los equipos creados son los siguientes:")
            listarEquipos()
            println("Ingrese el nombre del equipo que desea eliminar (Ej:Equipo-Quito)")
            var nombre= readLine().toString()
            eliminarArchivo(nombre,"Equipos")
            println("***Se ha eliminado el equipo ${nombre} con exito")

        }
        else -> println("Por favor seleccione una opción válida")
}
        println("Los equipos de futbol creados son los siguientes:")
        println("Escoja una opcion: \n" +
                "1.Crear un equipo\n" +
                "2.Ver la informacion de un equipo\n" +
                "3.Actualizar un equipo\n" +
                "4.Eliminar un equipo\n" +
                "5. Atras\n" +
                "Ingrese simplemente el numero de la opcion que desea (Ej: 1)")
        opcion = readLine()
    }}
    fun menuJugador(){
        println("Escoja una opcion: \n" +
                "1.Crear un jugador\n" +
                "2.Ver la informacion de un jugador\n" +
                "3.Actualizar la informacion de un jugador\n" +
                "4.Eliminar un jugador\n" +
                "5. Atras\n"+
                "Ingrese simplemente el numero de la opcion que desea (Ej: 1)")

        var opcion = readLine()
        while(opcion!="5"){
        when(opcion) {
            ("1") -> {

            }
            }
    }
}

fun leerArchivo(nombre: String,directorio:String){
    val inputStream: InputStream = File ("${directorio}/${nombre}.txt").inputStream()
    val inputString = inputStream.reader().use {it.readText()}
    println (inputString)
}
fun leerPorLineas(nombre: String,directorio:String):ArrayList<String>{
    val inputStream: InputStream = File("${directorio}/${nombre}.txt").inputStream()
    val lineas = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineas.add(it) } }
    var n = 1
    var lista= ArrayList<String>()
    lineas.forEach {
        if(n==1){
           lista.add(it.substring(18))
        }
        if(n==2){
            lista.add(it.substring(18))
        }
        if(n==3){
            lista.add(it.substring(18))
        }
        if(n==4){
            lista.add(it.substring(19))
        }
        if(n==5){
            lista.add(it.substring(20))
        }
         }
    return lista
}
fun listarEquipos(){
    val dir = "Equipos"
    val lineas = mutableListOf<String>()
    Files.walk(Paths.get(dir))
        .filter { Files.isRegularFile(it) }
        .forEach { println(it.toString().substring(8,it.toString().length-4))
        }
}

fun escribirArchivoEquipo(informacion:String, nombre: String?){
    File("Equipos","Equipo-${nombre}.txt").printWriter().use { out -> out.println(informacion) }
}
fun eliminarArchivo(nombre: String,directorio: String){
    File("${directorio}","${nombre}.txt").delete()
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

fun verdaderoFalso(valor: Boolean?):String{
    if(valor == true){
        return "Si"
    }
    else{
        return "No"
    }
}


class EquipoFutbol(
    protected var nombreEquipo: String?,
    protected var precioEquipo: Double?,
    protected var anioFundacion: Int?,
    protected var perteneceFEF:Boolean?,
    protected var numVictorias: Int?
){

    init {
        nombreEquipo
        precioEquipo
        anioFundacion
        perteneceFEF
        numVictorias
        crearEquipo(nombreEquipo)

    }
    fun crearEquipo(nombreEquipo: String?){
        escribirArchivoEquipo(infoEquipo(),nombreEquipo)

    }
    fun infoEquipo():String{
        var info:String
        var perteneceF=verdaderoFalso(perteneceFEF)
        info="Nombre del Equipo:$nombreEquipo\n" +
                "Precio del equipo:$precioEquipo\n" +
                "Anio de fundacion:$anioFundacion\n" +
                "Pertenece a la FEF:$perteneceF\n" +
                "Numero de victorias:$numVictorias"
        return info

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