package com.example.movcomphacp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import io.sentry.Sentry
import io.sentry.SentryLevel

class MainActivity : AppCompatActivity() {
    var numglobal=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Alexis
        setContentView(R.layout.activity_main)
        Sentry.captureMessage("testing SDK setup", SentryLevel.INFO);
        var num=0
        val botonNum=findViewById<Button>(R.id.btn_num)
        val textNum=findViewById<TextView>(R.id.num)
        textNum.setText("Número: ${num}")
        botonNum
            .setOnClickListener{
                num++
                textNum.setText("Número: ${num}")
            }
    }
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.run{
            putString("Texto",numglobal)
        }
        super.onSaveInstanceState(outState)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textoRecuperado:String?=savedInstanceState.getString("textoGuardado")
        if(textoRecuperado!=null){

            //textoGlobal=textoRecuperado
        }
    }

}