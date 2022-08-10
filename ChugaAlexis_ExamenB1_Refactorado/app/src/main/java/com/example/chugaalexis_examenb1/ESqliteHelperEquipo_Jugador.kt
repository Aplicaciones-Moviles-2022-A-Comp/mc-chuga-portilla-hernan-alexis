package com.example .chugaalexis_examenb1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.chugaalexis_examenb1.EquipoBaseDeDatos
import com.example.chugaalexis_examenb1.EquipoFutbol
import com.example.chugaalexis_examenb1.JugadorFutbol

class ESqliteHelperEquipo_Jugador(
    contexto: Context?,
) : SQLiteOpenHelper(contexto, "moviles", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador:ArrayList<String> = arrayListOf(
            """
               CREATE TABLE JUGADOR(
               id INTEGER PRIMARY KEY,
               nombre VARCHAR(50),
               fechaN VARCHAR(50),
               estatura VARCHAR(50),
               posicion VARCHAR(50),
               esEcuatoriano VARCHAR(50)
               );
             ""","""
               CREATE TABLE EQUIPO(
               id INTEGER PRIMARY KEY,
               nombre VARCHAR(50),
               precio VARCHAR(50),
               anio_fundacion VARCHAR(50),
               pertenece_FEF VARCHAR(50),
               num_victorias VARCHAR(50)
               );
            """)
        for (i in scriptSQLCrearTablaEntrenador){
            db!!.execSQL(i)
        }
        Log.i("creart", "Equipos")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun crearEquipo(id:Int,nombre:String, precio:String,anio:String,
                    perteneceFEF:String,numVictorias:String ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id",id)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("precio", precio)
        valoresAGuardar.put("anio_fundacion", anio)
        valoresAGuardar.put("pertenece_FEF", perteneceFEF)
        valoresAGuardar.put("num_victorias", numVictorias)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "EQUIPO",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true

    }

    fun listarEquipos(): ArrayList<EquipoFutbol>{
        var lista = arrayListOf<EquipoFutbol>()
        var equipo: EquipoFutbol?
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM EQUIPO"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                equipo=EquipoFutbol(0,"",0.0,0,"",0)
                equipo!!.idEquipo= resultadoConsultaLectura.getInt(0)
                equipo.nombreEquipo= resultadoConsultaLectura.getString(1)
                equipo.precioEquipo= resultadoConsultaLectura.getString(2).toDouble()
                equipo.anioFundacion= resultadoConsultaLectura.getString(3).toInt()
                equipo.perteneceFEF= resultadoConsultaLectura.getString(4)
                equipo.numVictorias= resultadoConsultaLectura.getString(5).toInt()
                lista.add(equipo)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }
    fun actualizarEquipo(idA:Int,nombre:String, precio:String,anio:String,
                         perteneceFEF:String,numVictorias:String ):Boolean{
        var lista= EquipoBaseDeDatos.TablaEquipo!!.listarEquipos()
        val id=lista[idA].idEquipo.toString()
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("precio", precio)
        valoresAActualizar.put("anio_fundacion", anio)
        valoresAActualizar.put("pertenece_FEF", perteneceFEF)
        valoresAActualizar.put("num_victorias", numVictorias)
        val resultadoActualizacion = conexionEscritura
            .update(
                "EQUIPO", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }
    fun eliminarEquipos(id:Int):Boolean{
        var lista= EquipoBaseDeDatos.TablaEquipo!!.listarEquipos()
        val idR=lista[id].idEquipo.toString()
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("EQUIPO","id=?", arrayOf(idR))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
    fun crearJugador(id:Int, nombre:String,fechaN:String, estatura: String,
                     posicion: String, esEcuatoriano: String):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id",id)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("fechaN", fechaN)
        valoresAGuardar.put("estatura", estatura)
        valoresAGuardar.put("posicion", posicion)
        valoresAGuardar.put("esEcuatoriano", esEcuatoriano)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "JUGADOR",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true

    }
    fun listarJugadores(): ArrayList<JugadorFutbol>{
        var lista = arrayListOf<JugadorFutbol>()
        var jugador: JugadorFutbol?
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM JUGADOR"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                jugador= JugadorFutbol(0,"","",0.0,"","")
                jugador!!.idJugador= resultadoConsultaLectura.getInt(0)
                jugador.nombre= resultadoConsultaLectura.getString(1)
                jugador.fechaNacimiento= resultadoConsultaLectura.getString(2)
                jugador.estatura= resultadoConsultaLectura.getString(3).toDouble()
                jugador.posicion= resultadoConsultaLectura.getString(4)
                jugador.esEcuatoriano= resultadoConsultaLectura.getString(5)
                lista.add(jugador)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }

    fun actualizarJugador(id:Int, nombre:String,fechaN:String, estatura: String,
                          posicion: String, esEcuatoriano: String ):Boolean{

        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("fechaN", fechaN)
        valoresAActualizar.put("estatura", estatura)
        valoresAActualizar.put("posicion", posicion)
        valoresAActualizar.put("esEcuatoriano", esEcuatoriano)
        val resultadoActualizacion = conexionEscritura
            .update(
                "JUGADOR", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }
    fun eliminarJugadores(id:Int):Boolean{
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("JUGADOR","id=?", arrayOf(id.toString()))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }



}