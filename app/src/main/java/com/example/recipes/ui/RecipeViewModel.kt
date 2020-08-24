package com.example.recipes.ui

import androidx.lifecycle.ViewModel
import com.example.recipes.data.db.entities.RecipeItem
import com.example.recipes.data.repositories.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: RecipeRepository
) : ViewModel(){

    fun upsert(recipe: RecipeItem) =
        CoroutineScope(Dispatchers.Main).launch {
            repository.upsert(recipe)
        }

    fun delete(recipe: RecipeItem) =
        CoroutineScope(Dispatchers.Main).launch {
            repository.delete(recipe)
        }

    fun getAllRecipes() =
        repository.getAllRecipes()

    fun getRecipesFromCategory(category: String) =
        repository.getRecipesFromCategory(category)

    fun getRecipe(name: String) =
        repository.getRecipeByName(name)
}