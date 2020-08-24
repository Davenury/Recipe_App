package com.example.recipes.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipes.data.repositories.RecipeRepository

class RecipeViewModelFactory(
    val repository: RecipeRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeViewModel(repository) as T
    }
}