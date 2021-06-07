package com.example.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ContactAdapter.ViewHolder>? = null
    lateinit var recyclerView: RecyclerView
    lateinit var add: FloatingActionButton
    lateinit var searchButton: Button
    lateinit var searchBage: EditText
    
    val lista = mutableListOf<Contact>()

    fun findNome(nome:String): Int {
      return lista.indexOfFirst { it.nome == nome }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = ContactAdapter(lista)
        recyclerView.adapter = adapter

        add = findViewById(R.id.add)
        searchButton = findViewById(R.id.search_button)
        searchBage = findViewById(R.id.search_badge)

        add.setOnClickListener{
            val intent = Intent(this, AddContact::class.java).apply{}
            startForResult.launch(intent)
        }

        searchButton.setOnClickListener{
            val pesquisa = searchBage.text.toString()
            val resultado = findNome(pesquisa)
            recyclerView.scrollToPosition(resultado)

            if (resultado == -1) {
                Toast.makeText(this, "Não encontrado", Toast.LENGTH_LONG).show()
            }
        }

    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when(it.resultCode){
            RESULT_OK -> {
                val intent = it.data
                val contato = intent?.getSerializableExtra("contato") as? Contact

                if (contato != null){
                    lista.add(contato)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }

}

