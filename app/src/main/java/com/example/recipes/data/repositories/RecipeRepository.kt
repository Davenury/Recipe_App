package com.example.recipes.data.repositories

import com.example.recipes.data.db.RecipeDatabase
import com.example.recipes.data.db.entities.RecipeItem

class RecipeRepository(
    private val db: RecipeDatabase
) {
    suspend fun upsert(recipe: RecipeItem) =
        db.getRecipeDao().upsert(recipe)

    suspend fun delete(recipe: RecipeItem) =
        db.getRecipeDao().delete(recipe)

    fun getAllRecipes() =
        db.getRecipeDao().getAllRecipes()

    fun getRecipesFromCategory(category: String) =
        db.getRecipeDao().getRecipesFromCategory(category)

    fun getRecipeByName(name: String) =
        db.getRecipeDao().getRecipeByName(name)
}