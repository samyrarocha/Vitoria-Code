package com.example.agenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>, Filterable {

    var context: Context
    var lista: MutableList<Contact>
    private var filterList:MutableList<Contact>
    private var filter:Pesquisa? = null

    constructor(context: Context, lista: MutableList<Contact>) : super() {
        this.context = context
        this.lista = lista
        this.filterList = lista
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {

        holder.nome.text = lista[position].nome
        holder.telefone.text = lista[position].telefone
        holder.preferencia.text = lista[position].preferencia
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nome: TextView = itemView.findViewById(R.id.name)
        var telefone: TextView = itemView.findViewById(R.id.phone)
        var preferencia: TextView = itemView.findViewById(R.id.setPreference)

    }

    /**
     *
     * Returns a filter that can be used to constrain data with a filtering
     * pattern.
     *
     *
     * This method is usually implemented by [android.widget.Adapter]
     * classes.
     *
     * @return a filter used to constrain data
     */
    override fun getFilter(): Filter {
        if (filter == null){
            filter = Pesquisa(filterList, this)
        }
        return filter as Pesquisa
    }

}