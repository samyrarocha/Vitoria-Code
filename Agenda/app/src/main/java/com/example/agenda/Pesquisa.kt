package com.example.agenda

import android.widget.Filter
import android.widget.Toast

class Pesquisa(private val filterList: MutableList<Contact>, private val adapter: ContactAdapter): android.widget.Filter() {

    /**
     *
     * Invoked in a worker thread to filter the data according to the
     * constraint. Subclasses must implement this method to perform the
     * filtering operation. Results computed by the filtering operation
     * must be returned as a [android.widget.Filter.FilterResults] that
     * will then be published in the UI thread through
     * [.publishResults].
     *
     *
     * **Contract:** When the constraint is null, the original
     * data must be restored.
     *
     * @param constraint the constraint used to filter the data
     * @return the results of the filtering operation
     *
     * @see .filter
     * @see .publishResults
     * @see android.widget.Filter.FilterResults
     */
    //Filter records
    override fun performFiltering(constraint: CharSequence?): FilterResults {

        var constraint1:CharSequence? = constraint
        val results = FilterResults()

        //check constraint validity
        if (constraint1 != null && constraint1.isNotEmpty()) {
            //to make case no sensitive
            constraint1 = constraint.toString().uppercase()
            //to store filter data
            val filteredModels = mutableListOf<Contact>()
            for (i in filterList.indices){
                //check if searched items matches any items in list
                if (filterList[i].nome.uppercase().contains(constraint1)){
                    //searched items matches, add to filterModel
                    filteredModels.add(filterList[i])
                    }
            }
            results.count = filteredModels.size
            results.values = filteredModels
        }
        else{
            //search is empty, show originl list
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }

    /**
     *
     * Invoked in the UI thread to publish the filtering results in the
     * user interface. Subclasses must implement this method to display the
     * results computed in [.performFiltering].
     *
     * @param constraint the constraint used to filter the data
     * @param results the results of the filtering operation
     *
     * @see .filter
     * @see .performFiltering
     * @see android.widget.Filter.FilterResults
     */

    //publish results after filtering
    override fun publishResults(constraint: CharSequence, results: FilterResults) {
        adapter.lista = results.values as MutableList<Contact>

        adapter.notifyDataSetChanged()
    }

}