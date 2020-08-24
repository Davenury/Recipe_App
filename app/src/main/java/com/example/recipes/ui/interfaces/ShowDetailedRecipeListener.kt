package com.example.recipes.ui.interfaces

import com.example.recipes.data.db.entities.RecipeItem

interface ShowDetailedRecipeListener {
    fun showDetailedRecipe(recipe: RecipeItem)
}