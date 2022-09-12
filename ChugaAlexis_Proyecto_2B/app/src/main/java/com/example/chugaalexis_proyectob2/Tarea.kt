package com.example.chugaalexis_proyectob2

import android.os.Parcel
import android.os.Parcelable

class Tarea (
    var idTarea: String?,
    var titulo: String?,
    var descripcion: String?,
    var fechaLimite: String?,
    var idCat: String?
): Parcelable {

    override fun toString(): String {
        return "${titulo} - ${fechaLimite}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()){}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idTarea)
        parcel.writeString(titulo)
        parcel.writeString(descripcion)
        parcel.writeString(fechaLimite)
        parcel.writeString(idCat)}

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tarea> {
        override fun createFromParcel(parcel: Parcel): Tarea {
            return Tarea(parcel)
        }

        override fun newArray(size: Int): Array<Tarea?> {
            return arrayOfNulls(size)
        }
    }}