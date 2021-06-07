package com.example.agenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val lista: MutableList<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

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

}