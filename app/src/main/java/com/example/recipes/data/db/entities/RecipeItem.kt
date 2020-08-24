package com.example.recipes.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipes.ui.rvClasses.Ingredient
import java.io.Serializable

@Entity(tableName = "Recipes")
class RecipeItem(
    @ColumnInfo(name = "recipe_name")
    var name: String,
    @ColumnInfo(name = "recipe_kcal")
    var kcal: Int,
    @ColumnInfo(name = "recipe_category")
    var category: String,
    @ColumnInfo(name = "steps")
    var steps: MutableList<String>,
    @ColumnInfo(name = "ingredients")
    var ingredients: MutableList<Ingredient>
) : Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    var id: Int? = null
}