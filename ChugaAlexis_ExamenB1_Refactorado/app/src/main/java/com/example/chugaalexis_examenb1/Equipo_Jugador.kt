package com.example.chugaalexis_examenb1


import android.os.Parcel
import android.os.Parcelable

class Equipo_Jugador (

    val idEquipo_Jugador: Int,
    var nombreE_J: String?,
    val idEquipo: Int,
    val idJugador: Int

) : Parcelable {

    override fun toString(): String {
        return "${nombreE_J}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idEquipo_Jugador)
        parcel.writeString(nombreE_J)
        parcel.writeInt(idEquipo)
        parcel.writeInt(idJugador)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Equipo_Jugador> {
        override fun createFromParcel(parcel: Parcel): Equipo_Jugador {
            return Equipo_Jugador(parcel)
        }

        override fun newArray(size: Int): Array<Equipo_Jugador?> {
            return arrayOfNulls(size)
        }
    }

}