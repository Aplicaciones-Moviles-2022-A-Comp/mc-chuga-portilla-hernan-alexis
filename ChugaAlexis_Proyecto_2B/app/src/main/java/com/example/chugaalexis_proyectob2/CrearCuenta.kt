package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearCuenta : AppCompatActivity() {
    val db = Firebase.firestore
    val usuarios = db.collection("Usuarios_Proyecto")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        }

    override fun onStart() {
        super.onStart()
        var txtCorreo= findViewById<EditText>(R.id.txt_correo_creacion)
        var txtUsuario= findViewById<EditText>(R.id.txt_usuario_creacion)
        var txtContraseña= findViewById<EditText>(R.id.txt_contrasena_creacion)
        var txtContraseñaC= findViewById<EditText>(R.id.txt_confirmar_contrasena_creacion)

        var btnCrearCuenta= findViewById<Button>(R.id.btn_crearCuenta)

        btnCrearCuenta.setOnClickListener{
            var usuario=hashMapOf(
                "correoElectronico" to txtCorreo.text.toString(),
                "Usuario" to txtUsuario.text.toString(),
                "Contraseña" to txtContraseña.text.toString(),
                "Contraseña2" to txtContraseñaC.text.toString())
            if (txtContraseña.text.toString()==txtContraseñaC.text.toString()){
                usuarios.add(usuario).addOnSuccessListener{
                    txtCorreo.text!!.clear()
                    txtUsuario.text!!.clear()
                    txtContraseña.text!!.clear()
                    txtContraseñaC.text!!.clear()


                }.addOnSuccessListener {
                    Log.i("Crear-User","Failed")
                }
                val mySnackbar = Snackbar.make(
                    findViewById(R.id.tv_conf_crearcuenta),
                    "Usuario creado con éxito",
                    Snackbar.LENGTH_SHORT
                ).show()
                val intentAddSucces = Intent(this, Login::class.java)
                startActivity(intentAddSucces)
            }
            else{
                val mySnackbar = Snackbar.make(
                    findViewById(R.id.tv_conf_crearcuenta),
                    "Las contraseñas no coinciden",
                    Snackbar.LENGTH_SHORT
                ).show()
                //Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            }

        }


        var btnAtrasCrearCuenta= findViewById<TextView>(R.id.txt_atras_crearCuenta)
        btnAtrasCrearCuenta.setOnClickListener {
            val intentAddCancel = Intent(this, Login::class.java)
            startActivity(intentAddCancel)
        }
    }


}