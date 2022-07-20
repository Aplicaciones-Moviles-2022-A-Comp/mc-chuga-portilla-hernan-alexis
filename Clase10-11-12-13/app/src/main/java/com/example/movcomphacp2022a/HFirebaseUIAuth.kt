package com.example.movcomphacp2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

class HFirebaseUIAuth : AppCompatActivity() {
    private val signInLauncher=registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){res: FirebaseAuthUIAuthenticationResult ->
        if(res.resultCode == RESULT_OK){
            if(res.idpResponse!=null){
                this.seLogeo(res.idpResponse!!)
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)

        val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
        btnLogin.setOnClickListener{
            val providers= arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build()
            )
            //Create and Launch sign-in intent
            val signIntent= AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signIntent)
        }

        val botonLogout=findViewById<Button>(R.id.btn_logout)
        botonLogout.setOnClickListener{
            AuthUI.getInstance()
                .signOut(this)
                .addOnCanceledListener {
                    seDeslogeo()
                }
        }
    }

    fun seLogeo(
        res:IdpResponse
    ){
        val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
        val botonLogout=findViewById<Button>(R.id.btn_logout)
        btnLogin.visibility= View.INVISIBLE
        botonLogout.visibility=View.VISIBLE
    }

    fun seDeslogeo(){
        val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
        val botonLogout=findViewById<Button>(R.id.btn_logout)
        btnLogin.visibility= View.VISIBLE
        botonLogout.visibility=View.INVISIBLE
    }
}