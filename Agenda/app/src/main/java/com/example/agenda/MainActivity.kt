package com.example.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.SearchView as AndroidWidgetSearchView


class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ContactAdapter.ViewHolder>? = null
    lateinit var recyclerView: RecyclerView
    lateinit var add: FloatingActionButton

    val listItem = mutableListOf<Contact>()
    lateinit var adapterItem: ContactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadRecyclerViewItems()

        add = findViewById(R.id.add)
        add.setOnClickListener{

            //send action AddContact class
            val intent = Intent(this, AddContact::class.java).apply{}
            startForResult.launch(intent)

        }

    }

    private fun loadRecyclerViewItems(){

        //init recyclerView
        recyclerView = findViewById(R.id.recyclerView)

        //set layout Manager
        layoutManager = LinearLayoutManager(this)

        //set layout to recyclerView
        recyclerView.layoutManager = layoutManager

        //setup adapter
        adapterItem = ContactAdapter(this, listItem)

        //set adapter to recyclerView
        recyclerView.adapter = adapterItem

    }

    //Call back from AddContact
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when(it.resultCode){
            RESULT_OK -> {
                val intent = it.data
                val contato = intent?.getSerializableExtra("contato") as? Contact

                if (contato != null){
                    listItem.add(contato)
                    adapterItem.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflate menu_main
        menuInflater.inflate(R.menu.menu_main,menu)

        //get action search
        val searchItem = menu?.findItem(R.id.searchButton)
        //make action search a SearchView
        val searchView = searchItem?.actionView as AndroidWidgetSearchView

        //search query listener
        searchView.setOnQueryTextListener(object : AndroidWidgetSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //search when search button clicked

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //search as and when type even a single letter in searchView
                adapterItem.filter.filter(newText)

                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //handle menu item clicks

        //get id of menu item clicked
        val id = item.itemId
        if (id == R.id.sortAction){
            sortDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortDialog() {
        //options to display in dialog
        val options = arrayOf("Ascending", "Descending")

        //init dialog
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Sort").setItems(options){ dialogInterface, i ->
            if (i == 0){
                //Ascending clicked
                dialogInterface.dismiss()
                sortAscending()
            }
            if (i == 1){
                //Descending clicked
                dialogInterface.dismiss()
                sortDescending()
            }
        }.show()
    }

    private fun sortDescending() {
        listItem.sortBy { it.nome }

        //refresh adapter after sorting
        adapterItem.notifyDataSetChanged()
    }

    private fun sortAscending() {
        listItem.sortByDescending { it.nome }

        //refresh adapter after sorting
        adapterItem.notifyDataSetChanged()
    }

}

