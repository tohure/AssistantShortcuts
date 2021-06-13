package io.tohure.shortcuts.flowTwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.tohure.shortcuts.R

class FactAdapter(private val listFacts: List<Fact>) :
    RecyclerView.Adapter<FactAdapter.FactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return FactViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.textView.text = holder.textView.context.resources.getString(
            listFacts[position].strResId
        )
    }

    override fun getItemCount() = listFacts.size

    class FactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.fact_title)
    }
}