package com.example.chugaalexis_examenb1


import android.os.Parcel
import android.os.Parcelable

class EquipoFutbol(

    var idEquipo: String?,
    var nombreEquipo: String?,
    var precioEquipo: Double?,
    var anioFundacion: Int?,
    var perteneceFEF: String?,
    var numVictorias: Int?

) : Parcelable {

    override fun toString(): String {
        return "${nombreEquipo}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idEquipo)
        parcel.writeString(nombreEquipo)
        parcel.writeDouble(precioEquipo!!)
        parcel.writeInt(anioFundacion!!)
        parcel.writeString(perteneceFEF)
        parcel.writeInt(numVictorias!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EquipoFutbol> {
        override fun createFromParcel(parcel: Parcel): EquipoFutbol {
            return EquipoFutbol(parcel)
        }

        override fun newArray(size: Int): Array<EquipoFutbol?> {
            return arrayOfNulls(size)
        }
    }


}