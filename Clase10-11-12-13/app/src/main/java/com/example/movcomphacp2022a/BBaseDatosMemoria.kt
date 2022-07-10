package com.example.movcomphacp2022a

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(11,"Adrian", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(22,"Vicente", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(33,"Carolina", "c@c.com")
                )
        }
    }
}