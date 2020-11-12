package com.jrodriguezh.transformers.ui.war.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.data.Match
import com.jrodriguezh.transformers.data.Transformer
import kotlinx.android.synthetic.main.list_item_transformer.view.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_match.view.*
import java.lang.StringBuilder

class MatchesAdapter(
    private val list: ArrayList<Match>
) : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_match, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = list[position]
        match.t1?.let {
            Picasso.get().load(it.team.icon).into(holder.t1icon)
            holder.t1name.text = it.name
        }
        match.t2?.let {
            Picasso.get().load(it.team.icon).into(holder.t2icon)
            holder.t2name.text = it.name
        }

        if (match.t1 == null) {
            holder.t1name.text = ""
            holder.t1icon.setImageDrawable(null)
        }
        if (match.t2 == null) {
            holder.t2name.text = ""
            holder.t2icon.setImageDrawable(null)
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        val t1icon: ImageView = itemsView.transformer1_icon
        val t2icon: ImageView = itemsView.transformer2_icon
        val t1name: TextView = itemsView.transformer1_name
        val t2name: TextView = itemsView.transformer2_name
    }
}