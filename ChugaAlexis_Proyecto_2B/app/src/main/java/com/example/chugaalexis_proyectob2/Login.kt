package com.example.chugaalexis_proyectob2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    val db = Firebase.firestore
    val usuarios = db.collection("Usuarios_Proyecto")
    val usuarioLogin=Usuario("","","","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    override fun onStart() {
        super.onStart()
        var txtUsuario= findViewById<EditText>(R.id.txt_usuario_login).text.toString()
        var txtContraseña= findViewById<EditText>(R.id.txt_contraseña_login).text.toString()

        val btnIngresar=findViewById<Button>(R.id.btn_ingresar)
        val crearCuenta = findViewById<TextView>(R.id.txt_crear_cuenta)
        var bandera=false

            btnIngresar.setOnClickListener {
                usuarios.get().addOnSuccessListener { result ->
                    for (document in result) {
                        if (document.data.get("Usuario").toString() == txtUsuario  &&
                            document.data.get("Contraseña").toString() == txtContraseña
                        ) {
                            bandera = true
                        }
                    }
                }
                if(bandera){

                    val intent = Intent(this, InicioCategorias::class.java)
                    startActivity(intent)
                }else{
                    val intent = Intent(this, InicioCategorias::class.java)
                    startActivity(intent)

                           }
        }
        crearCuenta.setOnClickListener {
            val intent = Intent(this, CrearCuenta::class.java)
            startActivity(intent)
        }

        val btnAtrasLogin=findViewById<Button>(R.id.txt_atras_login)
        btnAtrasLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun iniciarDatos2(){
        var categoriaSeleccionada=""
        val db = Firebase.firestore
        val categorias = db.collection("Categorias_Proyecto")

        categorias.get().addOnSuccessListener { result ->
            for(document in result){
                if(document.data.get("Nombre").toString()=="Trabajo"){
                    categoriaSeleccionada= document.id.toString()
                }
            }}
        val catSubColeccion = categorias.document("${categoriaSeleccionada}")
            .collection("Tareas")
        var tarea=hashMapOf(
            "Titulo" to "Completar Informe",
            "Fecha Limite" to "13-09-2022",
            "Descripcion" to "Urgente presentar",
            "IDCategoria" to categoriaSeleccionada,)
        catSubColeccion.add(tarea)
    }

}