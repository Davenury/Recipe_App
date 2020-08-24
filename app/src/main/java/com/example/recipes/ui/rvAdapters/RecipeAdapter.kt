package com.example.recipes.ui.rvAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.data.db.entities.RecipeItem
import com.example.recipes.ui.RecipeViewModel
import com.example.recipes.ui.interfaces.ShowDetailedRecipeListener
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter (
    var recipes: List<RecipeItem>,
    private val viewModel: RecipeViewModel,
    val detailedRecipeListener: ShowDetailedRecipeListener
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){
    inner class RecipeViewHolder(item: View): RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.itemView.tvRecipeName.text = currentRecipe.name
        holder.itemView.tvRecipeCategory.text = currentRecipe.category
        holder.itemView.tvRecipeKcal.text = "${currentRecipe.kcal}"

        holder.itemView.ivRecipeDelete.setOnClickListener {
            viewModel.delete(currentRecipe)
        }

        holder.itemView.ivRecipeArrow.setOnClickListener {
            showRecipeInDetails(currentRecipe)
        }
    }

    fun showRecipeInDetails(recipe: RecipeItem){
        detailedRecipeListener.showDetailedRecipe(recipe)
    }
}