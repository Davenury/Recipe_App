package com.example.recipes.ui.rvAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import kotlinx.android.synthetic.main.item_step.view.*

class StepAdapter(
    val steps: MutableList<String>
) : RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    inner class StepViewHolder(stepView: View): RecyclerView.ViewHolder(stepView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepAdapter.StepViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step, parent, false)
        return StepViewHolder(view)
    }

    override fun getItemCount(): Int {
        return steps.size
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.itemView.apply {
            etItemStepName.text = steps[position]
        }
    }
}