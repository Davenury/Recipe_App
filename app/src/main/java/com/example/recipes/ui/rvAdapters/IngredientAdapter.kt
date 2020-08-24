package com.example.recipes.ui.rvAdapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.ui.rvClasses.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(
    var ingredients: List<Ingredient>
) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(ingView: View): RecyclerView.ViewHolder(ingView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.itemView.apply{
            tvItemIngName.text = ingredients[position].name
            tvQuantity.text = "${ingredients[position].quantity}"
            tvMeasurement.text = ingredients[position].measurement
        }
    }

    fun getIngredientByPosition(position: Int) : Ingredient{
        return ingredients[position]
    }

    fun getAllIngredients() : List<Ingredient>{
        return ingredients
    }
}