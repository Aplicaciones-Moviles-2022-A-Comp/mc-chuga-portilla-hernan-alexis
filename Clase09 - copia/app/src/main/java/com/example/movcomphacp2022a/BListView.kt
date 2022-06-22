package com.example.movcomphacp2022a

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class BListView: AppCompatActivity() {
 //   val arreglo:ArrayList<Int> = arrayListOf(1,2,3,4,5)
    val arreglo: ArrayList<BEntrenador> = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist)

        val listView=findViewById<ListView>(R.id.lv_list_view)
        val adapatador=ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter=adapatador
        adapatador.notifyDataSetChanged()

        val botonAnadirListView=findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView
            .setOnClickListener{
                anadirNumero(adapatador)
            }
        registerForContextMenu(listView)
    }
    fun anadirNumero(
    //    adapatador:ArrayAdapter<Int>
        adapatador:ArrayAdapter<BEntrenador>
    ){
        arreglo.add(
            BEntrenador("Chuga","Aexis")
        )
        adapatador.notifyDataSetChanged()
    }

    var idItemSeleccionado=0
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenamos las pociones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        //Obtener el id del ArrayListSeleccionado
        val info =menuInfo as AdapterView.AdapterContextMenuInfo
        val id= info.position
        idItemSeleccionado=id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar->{
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar->{
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else->super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{dialog, which ->  }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val opciones= resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia= booleanArrayOf(
            true, //Lunes seleccionado
            false,//Martes no seleccionado
            false// Miercoles no seleccionado
        )
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            { dialog,
                which,
                isChecked ->
                "Dio clic en el item ${which}"
            }
        )
        var dialogo=builder.create()
        dialogo.show()
    }

}