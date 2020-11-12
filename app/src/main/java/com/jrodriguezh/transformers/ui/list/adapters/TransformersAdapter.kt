package com.jrodriguezh.transformers.ui.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.data.Transformer
import kotlinx.android.synthetic.main.list_item_transformer.view.*
import com.squareup.picasso.Picasso
import java.lang.StringBuilder

class TransformersAdapter(
    private val list: ArrayList<Transformer>
) : RecyclerView.Adapter<TransformersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_transformer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transformer = list[position]
        val spec = transformer.spec
        Picasso.get().load(transformer.team.icon).into(holder.icon)
        holder.name.text = transformer.name

        val builder  = StringBuilder()
        with(builder){
            append(spec.overall().let { "OVR: $it" }).append(" ")
            append(spec.skill.let { "SKILL: $it" }).append(" ")
            append(spec.strength.let { "STR: $it" }).append(" ")
            append(spec.intelligence.let { "INT: $it" }).append(" ")
            append(spec.speed.let { "SPD: $it" }).append(" ")
            append(spec.endurance.let { "END: $it" }).append(" ")
            append(spec.firepower.let { "PWR: $it" })
        }

        holder.overall.text = builder.toString()
        holder.itemView.setOnClickListener{ itemClick!!(transformer) }
    }

    var itemClick : ((Transformer) -> Unit)? = null

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        val icon: ImageView = itemsView.icon
        val name: TextView = itemsView.firstLine
        val overall: TextView = itemsView.secondLine
    }
}