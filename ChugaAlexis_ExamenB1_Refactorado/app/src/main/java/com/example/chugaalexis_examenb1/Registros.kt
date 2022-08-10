package com.example.chugaalexis_examenb1


class Registros {

    companion object{

        var arregloEquipos_Jugadores = arrayListOf<Equipo_Jugador>()

        init {
            // Equipos

            EquipoBaseDeDatos.TablaEquipo!!.crearEquipo(1,"Liga de Quito","250000.00","1984","Si","70")

            EquipoBaseDeDatos.TablaEquipo!!.crearEquipo(2,"Imbabura","100000.00","2002","No","50")

            EquipoBaseDeDatos.TablaEquipo!!.crearEquipo(3,"Barcelona","150000.00","1975","Si","60")


            // Jugadores
            EquipoBaseDeDatos.TablaEquipo!!.crearJugador(1,"Alexis Chugá","1999-03-25","1.75","Delantero","Si")

            EquipoBaseDeDatos.TablaEquipo!!.crearJugador(2,"Antonio Valencia","1992-01-15","1.85","Defensa","Si")

            EquipoBaseDeDatos.TablaEquipo!!.crearJugador(3,"Lionel Messi","1986-05-05","1.70","Medio Campista","No")


            // Equipos y jugadores
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(1, 1,2)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(2, 1, 3)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(3,2, 3)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(4,2,1)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(5,2,2)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(6,3,2)
            )

        }

    }

}