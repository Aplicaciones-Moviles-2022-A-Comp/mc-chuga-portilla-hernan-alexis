package com.example.chugaalexis_proyectob2

import android.os.Parcel
import android.os.Parcelable

class Usuario (
    var idUsuario: String?,
    var usuario: String?,
    var correo: String?,
    var contraseña: String?
    ): Parcelable {

        override fun toString(): String {
            return "${usuario}"
        }

        constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()){}

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(idUsuario)
            parcel.writeString(usuario)
            parcel.writeString(correo)
            parcel.writeString(contraseña)}

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }

}