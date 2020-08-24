package com.example.recipes.ui.interfaces

import com.example.recipes.ui.rvClasses.Ingredient

interface AddIngredientListener {
    fun onAddButtonClicked(ingredient: Ingredient)
}