package com.example.recipes.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipes.data.db.entities.RecipeItem

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe: RecipeItem)

    @Query("SELECT * from Recipes")
    fun getAllRecipes(): LiveData<List<RecipeItem>>

    @Query("SELECT * from Recipes where recipe_category = :category")
    fun getRecipesFromCategory(category: String): LiveData<List<RecipeItem>>

    @Query("select * from Recipes where recipe_name like :name")
    fun getRecipeByName(name: String): LiveData<List<RecipeItem>>

    @Delete
    suspend fun delete(recipe: RecipeItem)
}