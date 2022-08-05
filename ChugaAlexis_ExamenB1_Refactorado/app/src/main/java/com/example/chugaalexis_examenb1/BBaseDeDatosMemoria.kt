package com.example.chugaalexis_examenb1


class BBaseDeDatosMemoria {

    companion object{
        var arregloEquipos = arrayListOf<EquipoFutbol>()
        var arregloJugadores = arrayListOf<JugadorFutbol>()
        var arregloEquipos_Jugadores = arrayListOf<Equipo_Jugador>()

        init {
            // Equipos
            arregloEquipos.add(
                EquipoFutbol(1,"Liga de Quito",250000.00,1984,"Si",70)
            )
            arregloEquipos.add(
                EquipoFutbol(2,"Imbabura",100000.00,2002,"No",50)
            )
            arregloEquipos.add(
                EquipoFutbol(3,"Barcelona",150000.00,1975,"Si",60)
            )

            // Jugadores
            arregloJugadores.add(
                JugadorFutbol(1,"Alexis Chugá","1999-03-25",1.75,"Delantero","Si")
            )
            arregloJugadores.add(
                JugadorFutbol(2,"Antonio Valencia","1992-01-15",1.85,"Defensa","Si")
            )
            arregloJugadores.add(
                JugadorFutbol(3,"Lionel Messi","1986-05-05",1.70,"Medio Campista","No")
            )

            // Equipos y jugadores
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(1, "Antonio Valencia", 1,2)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(2, "Lionel Messi", 1, 3)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(3, "Lionel Messi",2, 3)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(4, "Alexis Chugá",2,1)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(5, "Antonio Valencia",2,2)
            )
            arregloEquipos_Jugadores.add(
                Equipo_Jugador(6, "Antonio Valencia",3,2)
            )

        }

    }

}