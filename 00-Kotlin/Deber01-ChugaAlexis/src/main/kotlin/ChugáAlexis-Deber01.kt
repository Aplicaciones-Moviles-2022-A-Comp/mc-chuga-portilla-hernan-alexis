import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main(){
    println("**********************************************************")
    println("ALEXIS CHUGA -- DEBER 01 -- APLICACIONES MOVILES")
    println("**********************************************************")
    println("Escoja al menu que desea ingresar:\n1. Menu de equipos \n2. Menu de jugadores\n" +
            "3. Menu de Equipos y sus jugadores \n4. Salir\n" +
            "Ingrese simplemente el numero de la opcion que desea (Ej: 1)")
    var opcion1= readLine()
    while(opcion1!="4"){
    when (opcion1){
        ("1")->{
            menuEquipo()
        }
        ("2")->{
           menuJugador()
        }
        ("3")->{
            menuEquipo_Jugador()
        }

    }
        println("Escoja al menu que desea ingresar:\n1. Menu de equipos \n2. Menu de jugadores\n" +
                "3. Menu de Equipos y sus jugadores \n4. Salir\n" +
                "Ingrese simplemente el numero de la opcion que desea (Ej: 1)")
        opcion1= readLine()
    }}
fun menuEquipo(){
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
            var perteneceFV=deStringaBoolean(perteneceF)
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
            println("El ${nombre} tiene la siguiente informacion")
            leerArchivo(nombre,"Equipos")
            var lista:ArrayList<String>
            lista=leerPorLineasEquipo("${nombre}","Equipos")
            println("Ingrese los nuevos datos, si no desea cambiar la informacion simplemente presione Enter y continue:")

            println("El nombre del equipo no es editable")
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

            var perteneceF2= readLine().toString()
            if(perteneceF2==""){perteneceF2= lista[3] }
            var perteneceFV=deStringaBoolean(perteneceF2)
            println("Ingrese el numero de victorias del equipo: ")
            var numV2= readLine()
            var numV:Int=0
            if(numV2==""){numV= lista[4]?.toInt() }
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
        else -> println("Por favor seleccione una opcion valida")
}
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
                println("Escogio la opcion 'Crear un jugador'")
                println("Complete los siguientes campos para crear el jugador: ")
                println("Ingrese el nombre del jugador: ")
                var nombreJ= readLine().toString()
                println("Ingrese la fecha de nacimiento del jugador, en el formato (aaaa-mm-dd): ")
                var fechaJ= readLine().toString()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-d")
                val date = LocalDate.parse(fechaJ, formatter)
                println("Ingrese la estatura del jugador: ")
                var estaturaJ= readLine()?.toFloat()
                println("Ingrese el posicion del jugador: ")
                var posicionJ= readLine().toString()
                println("Ingrese S, si el jugador es ecuatoriano, o N, si el jugador es extranjero: ")
                var ecuatorianoJ= readLine().toString()
                var ecuatorianoJB=deStringaBoolean(ecuatorianoJ)
                println("Ingrese el nombre del equipo al que pertenece el jugador (Ej:Equipo-Quito): ")
                var equipo= readLine().toString()
                println()
                Jugador(nombreJ,date,estaturaJ,posicionJ,ecuatorianoJB,equipo)
                println("***Se ha creado el jugador ${nombreJ} con exito")
            }
            ("2") ->{
                println("Escogio la opcion de ver la informacion de un jugador")
                println("Los jugadores creados son los siguientes:")
                listarJugadores()
                println("Ingrese el nombre del jugador del cual desea visualizar su informacion(Ej:Jugador-Luis)")
                var nombre= readLine().toString()
                leerArchivo(nombre,"Jugadores")
                println("Escoja una de las siguientes opciones:\n" +
                        "1.Ver la informacion de otro jugador\n" +
                        "2.Regresar al menu de jugadores")
                var opcion2= readLine().toString()
                while(opcion2!="2"){
                    println("Los jugadores creados son los siguientes:")
                    listarJugadores()
                    println("Ingrese el nombre del jugador del cual desea visualizar su informacion(Ej:Jugador-Luis)")
                    var nombre= readLine().toString()
                    leerArchivo(nombre,"Jugadores")
                    println("\nEscoja una de las siguientes opciones:\n" +
                            "1.Ver la informacion de otro jugador\n" +
                            "2.Regresar al menu de jugadores")
                    opcion2= readLine().toString()
                }
            }
            ("3") ->{
                println("Escogio la opcion de actualizar un jugador")
                println("Los jugadores creados son los siguientes:")
                listarJugadores()
                println("Ingrese el nombre del jugador del cual desea actualizar su informacion(Ej:Jugador-Luis)")
                var nombre= readLine().toString()
                println("El ${nombre} tiene la siguiente informacion")
                leerArchivo(nombre,"Jugadores")
                var lista:ArrayList<String>
                lista=leerPorLineasJugador("${nombre}","Jugadores")
                println("Ingrese los nuevos datos, si no desea cambiar la informacion simplemente presione Enter y continue:")
                println("El nombre del jugador no es editable")
                println("Ingrese la fecha de nacimiento del jugador en el formato (yyyy-MM-dd): ")
                var fecha2= readLine()?.toString()
                val date:LocalDate
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-d")
                if(fecha2==""){date = LocalDate.parse(lista[1], formatter)
                }
                else{date = LocalDate.parse(fecha2, formatter)
                }
                println("Ingrese la estatura del jugador: ")
                var estatura2= readLine()?.toString()
                var estatura:Float?
                if(estatura2==""){estatura= lista[2].toFloat()
                }
                else{estatura= estatura2?.toFloat()
                }
                println("Ingrese la posicion del jugador: ")
                var posicion= readLine()
                if(posicion==""){posicion=lista[3].toString()}

                println("Ingrese S, si el jugador es ecuatoriano, o N, si el jugador es extranjero: ")
                var ecuatoriano=""
                var ecuatoriano2= readLine().toString()
                if(ecuatoriano2==""){ecuatoriano2= lista[4] }
                else{ecuatoriano= ecuatoriano2
                }
                var ecuatorianoFV=deStringaBoolean(ecuatoriano)
                println("Ingrese el nombre del equipo al que pertenece el jugador (Ej:Equipo-Quito): ")
                var equipo= readLine()
                if(equipo==""){equipo=lista[5].toString()}

                Jugador(nombre.substring(8),date,estatura,posicion,ecuatorianoFV,equipo)
                println("***Se ha actualizado el jugador ${nombre.substring(8)} con exito")
            }
            ("4") ->{
                println("Escogio la opcion de eliminar un jugador")
                println("Los jugadores creados son los siguientes:")
                listarJugadores()
                println("Ingrese el nombre del jugador que desea eliminar (Ej:Jugador-Luis)")
                var nombre= readLine().toString()
                eliminarArchivo(nombre,"Jugadores")
                println("***Se ha eliminado el ${nombre} con exito")

            }
            else -> println("Por favor seleccione una opcion valida")
        }
            println("Escoja una opcion: \n" +
                    "1.Crear un Jugador\n" +
                    "2.Ver la informacion de un Jugador\n" +
                    "3.Actualizar un Jugador\n" +
                    "4.Eliminar un Jugador\n" +
                    "5. Atras\n" +
                    "Ingrese simplemente el numero de la opcion que desea (Ej: 1)")
            opcion = readLine()

        }
    }
fun menuEquipo_Jugador(){
    println("Escoja una opcion: \n" +
            "1.Listar los jugadores de un equipo\n" +
            "2.Atras\n")
    var opcion = readLine()
    while(opcion!="2"){
        println("Los equipos creados son los siguientes:")
        listarEquipos()
        println("Ingrese el nombre del equipo del cual desea visualizar sus jugadores (Ej:Equipo-Quito)")
        var nombre= readLine().toString()
        println("Los jugadores del ${nombre} son:")
        equipo_jugador(nombre)
    println()
        println("Escoja una opcion: \n" +
                "1.Listar los jugadores de un equipo\n" +
                "2.Atras\n")
        opcion = readLine()
}}

fun leerArchivo(nombre: String,directorio:String){
    val inputStream: InputStream = File ("${directorio}/${nombre}.txt").inputStream()
    val inputString = inputStream.reader().use {it.readText()}
    println (inputString)
}
fun leerPorLineasEquipo(nombre: String, directorio:String):ArrayList<String>{
    val inputStream: InputStream = File("${directorio}/${nombre}.txt").inputStream()
    val lineas = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineas.add(it) } }
    var n = 0
    var lista= ArrayList<String>()
    for (i in lineas){
        if(n==0){
            lista.add(i.substring(19))
        }
        if(n==1){
            lista.add(i.substring(19))
        }
        if(n==2){
            lista.add(i.substring(19))
        }
        if(n==3){
            lista.add(i.substring(20))
        }
        if(n==4){
            lista.add(i.substring(21))
        }
        n++
    }
    return lista
}
fun leerPorLineasJugador(nombre: String, directorio:String):ArrayList<String>{
    val inputStream: InputStream = File("${directorio}/${nombre}.txt").inputStream()
    val lineas = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineas.add(it) } }
    var n = 0
    var lista= ArrayList<String>()
    for (i in lineas){
        if(n==0){
            lista.add(i.substring(19))
        }
        if(n==1){
            lista.add(i.substring(20))
        }
        if(n==2){
            lista.add(i.substring(9))
        }
        if(n==3){
            lista.add(i.substring(9))
        }
        if(n==4){
            lista.add(i.substring(12))
        }
        if(n==5){
            lista.add(i.substring(19))
        }
        n++
    }
    return lista
}
fun listarEquipos(){
    val dir = "Equipos"
    val lineas = mutableListOf<String>()
    Files.walk(Paths.get(dir))
        .filter { Files.isRegularFile(it) }
        .forEach { println("* "+it.toString().substring(8,it.toString().length-4))
        }
}
fun listarJugadores(){
    val dir = "Jugadores"
    val lineas = mutableListOf<String>()
    Files.walk(Paths.get(dir))
        .filter { Files.isRegularFile(it) }
        .forEach { println("* "+it.toString().substring(10,it.toString().length-4))
        }}
fun equipo_jugador(equipo:String){
    val dir = "Jugadores"
    val lineas = mutableListOf<String>()
    Files.walk(Paths.get(dir))
        .filter { Files.isRegularFile(it) }
        .forEach {
            var lista=leerPorLineasJugador(it.toString().substring(10,it.toString().length-4),"Jugadores")
            if(lista[5]==equipo){
            println("*"+it.toString().substring(10,it.toString().length-4))
        }}
}


fun escribirArchivoEquipo(informacion:String, nombre: String?){
    File("Equipos","Equipo-${nombre}.txt").printWriter().use { out -> out.println(informacion) }
}
fun eliminarArchivo(nombre: String,directorio: String){
    File("${directorio}","${nombre}.txt").delete()
}
fun deStringaBoolean(dato: String?): Boolean{
    var datoM= dato?.uppercase()
    if (datoM=="S"||datoM=="Si"){
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

fun escribirArchivoJugador(informacion:String, nombre: String?){
    File("Jugadores","Jugador-${nombre}.txt").printWriter().use { out -> out.println(informacion) }
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
        info="Nombre del Equipo: $nombreEquipo\n" +
                "Precio del equipo: $precioEquipo\n" +
                "Anio de fundacion: $anioFundacion\n" +
                "Pertenece a la FEF: $perteneceF\n" +
                "Numero de victorias: $numVictorias"
        return info

    }
}
class Jugador(
    protected var nombreJugador: String?,
    protected var fechaNacimiento: LocalDate?,
    protected var estatura: Float?,
    protected var posicion:String?,
    protected var ecuatoriano: Boolean?,
    protected var equipo: String?
){
    init {
        nombreJugador
        fechaNacimiento
        estatura
        posicion
        ecuatoriano
        equipo
        crearJugador(nombreJugador)
    }
    fun crearJugador(nombreJugador: String?){
        escribirArchivoJugador(infoJugador(),nombreJugador)
    }
    fun infoJugador():String{
        var info:String
        var ecuatorianoF=verdaderoFalso(ecuatoriano)
        info="Nombre del Jugador:$nombreJugador\n" +
                "Fecha de nacimiento:$fechaNacimiento\n" +
                "Estatura:$estatura\n" +
                "Posicion:$posicion\n" +
                "Ecuatoriano:$ecuatorianoF\n" +
                "Equipo del jugador:$equipo\n"
        return info

    }
}