package com.example.movcomphacp2022a

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO=401
    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
        if (result.resultCode== Activity.RESULT_OK){
            if(result.data!=null){
                val data=result.data
                Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
            }

        }
    }
    val contenidoIntentImplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            if (result.data != null) {
                if (result.data!!.data != null) {
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(
                        uri,
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(
                        indiceTelefono!!
                    )
                    cursor?.close()
                    Log.i("intent-epn", "Telefono ${telefono}")
                }
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Alexis
        setContentView(R.layout.activity_main)
       // Sentry.captureMessage("testing SDK setup", SentryLevel.INFO);
        EBaseDeDatos.TablaEntrenador=ESqliteHelperEntrenador(this)


        val botonCicloVida=findViewById<Button>(R.id.btn_ciclo_vida)
      /*  botonCicloVida
            .setOnClickListener{
                irActividad(ACicloVida::class.java)

            }*/
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
/*
        val botonTrabClass02=findViewById<Button>(R.id.btn_trabclass02)
        botonTrabClass02
            .setOnClickListener{
                irActividad(Trabajo_Class02::class.java)
            }*/
        val botonIntent=findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentExplicito.launch(intentConRespuesta)
//                startActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_IMPLICITO)
            }
        val botonCRUD_Entrenador = findViewById<Button>(R.id.btn_CRUD)
        botonCRUD_Entrenador
            .setOnClickListener {
                irActividad(CRUD_Entrenador::class.java)
            }
        val botonRecyclerView = findViewById<Button>(R.id.btn_recycler)
        botonRecyclerView
            .setOnClickListener {
                irActividad(GRecyclerView::class.java)
            }
        val botonIrFirebase = findViewById<Button>(R.id.btn_ir_firebase)
        botonIrFirebase
            .setOnClickListener {
                irActividad(HFirebaseUIAuth::class.java)
            }
    }
    fun irActividad(
        clase:Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito= Intent(this,clase)
        intentExplicito.putExtra("nombre","Alexis")
        intentExplicito.putExtra("apellido","Chuga")
        intentExplicito.putExtra("edad",23)
        intentExplicito.putExtra("entrenadorPrincipal",BEntrenador(1,"Alexis","Paleta"))
        //startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
        contenidoIntentExplicito.launch(intentExplicito)

    }
}