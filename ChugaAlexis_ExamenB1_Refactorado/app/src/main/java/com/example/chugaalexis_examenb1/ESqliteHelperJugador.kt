package com.example.chugaalexis_examenb1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperJugador(
    contexto: Context?,
) : SQLiteOpenHelper(contexto, "moviles", null, 1) {

    override fun onCreate(db2: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador2 =
            """
               CREATE TABLE JUGADOR(
               id_J INTEGER PRIMARY KEY,
               nombre_J VARCHAR(50),
               fechaN VARCHAR(50),
               estatura VARCHAR(50),
               posicion VARCHAR(50),
               esEcuatoriano VARCHAR(50)
               ) 
            """.trimIndent()
        db2?.execSQL(scriptSQLCrearTablaEntrenador2)
        Log.i("creart", "Jugadores")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun crearJugador(id:Int, nombre:String,fechaN:String, estatura: String,
    posicion: String, esEcuatoriano: String):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_J",id)
        valoresAGuardar.put("nombre_J", nombre)
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

    fun actualizarJugador(idA:Int, nombre:String,fechaN:String, estatura: String,
                          posicion: String, esEcuatoriano: String ):Boolean{
        var lista= JugadorBaseDeDatos.TablaJugador!!.listarJugadores()
        val id=lista[idA].idJugador.toString()
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre_J", nombre)
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
        var lista= JugadorBaseDeDatos.TablaJugador!!.listarJugadores()
        val idR=lista[id].idJugador.toString()
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("JUGADOR","id=?", arrayOf(idR))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
}