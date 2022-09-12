package com.example.chugaalexis_proyectob2

import android.os.Parcel
import android.os.Parcelable

class Categoria (
    var idCategoria: String?,
    var nombre: String?,
    var descripcion: String?
): Parcelable {

    override fun toString(): String {
         return "${nombre}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()){}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idCategoria)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)}

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Categoria> {
        override fun createFromParcel(parcel: Parcel): Categoria {
            return Categoria(parcel)
        }

        override fun newArray(size: Int): Array<Categoria?> {
            return arrayOfNulls(size)
        }
    }

}