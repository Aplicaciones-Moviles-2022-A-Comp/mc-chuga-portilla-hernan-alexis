package com.example.movcomphacp2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        val listaEntrenador= arrayListOf<BEntrenador>()
        listaEntrenador
            .add(BEntrenador(1,"Alexis","Chugá"))
        listaEntrenador
            .add(BEntrenador(1,"Hernan","Portilla"))
        val recyclerView= findViewById<RecyclerView>(R.id.rv_entrenadores)
        inicializarRecyclerView(listaEntrenador,recyclerView)
    }

    fun inicializarRecyclerView(
        lista:ArrayList<BEntrenador>,
        recyclerView: RecyclerView
    ){
        val adaptador=FRecyclerViewAdaptadorNombreCedula(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarlikes(){
        totalLikes= totalLikes+1
        val totalLikestextView=findViewById<TextView>(R.id.tv_total_likes)
        totalLikestextView.setText(totalLikes.toString())
    }
}