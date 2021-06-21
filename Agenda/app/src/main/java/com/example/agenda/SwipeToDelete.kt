package com.example.agenda

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class SwipeToDelete (var adapter: ContactAdapter): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    lateinit var deletedContact: Contact

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.absoluteAdapterPosition
        deletedContact = adapter.lista.get(position)
        adapter.deleteItem(position)

    }
}