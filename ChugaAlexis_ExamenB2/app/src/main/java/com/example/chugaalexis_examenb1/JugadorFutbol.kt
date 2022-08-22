package com.example.chugaalexis_examenb1



import android.os.Parcel
import android.os.Parcelable

class JugadorFutbol (
    var idJugador: String?,
    var idEquipo_J:String?,
    var nombre:String?,
    var fechaNacimiento: String?,
    var estatura: Double?,
    var posicion: String?,
    var esEcuatoriano:String?

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idEquipo_J)
        parcel.writeString(idJugador)
        parcel.writeString(nombre)
        parcel.writeString(fechaNacimiento)
        parcel.writeDouble(estatura!!)
        parcel.writeString(posicion)
        parcel.writeString(esEcuatoriano)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${nombre}"
    }

    companion object CREATOR : Parcelable.Creator<JugadorFutbol> {
        override fun createFromParcel(parcel: Parcel): JugadorFutbol {
            return JugadorFutbol(parcel)
        }

        override fun newArray(size: Int): Array<JugadorFutbol?> {
            return arrayOfNulls(size)
        }
    }


}