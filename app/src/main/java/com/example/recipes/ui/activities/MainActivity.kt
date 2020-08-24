package com.example.recipes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.recipes.R
import com.example.recipes.RecipeDetailFragment
import com.example.recipes.data.db.RecipeDatabase
import com.example.recipes.data.db.entities.RecipeItem
import com.example.recipes.data.repositories.RecipeRepository
import com.example.recipes.ui.AddRecipeFragment
import com.example.recipes.ui.MyRecipesFragment
import com.example.recipes.ui.RecipeViewModel
import com.example.recipes.ui.RecipeViewModelFactory
import com.example.recipes.ui.interfaces.ShowDetailedRecipeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipeDatabase = RecipeDatabase(this)
        val repository = RecipeRepository(recipeDatabase)
        val factory = RecipeViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(RecipeViewModel::class.java)
        getFirstScreenReady(viewModel)
    }

    private fun getFirstScreenReady(viewModel: RecipeViewModel){
        val myRecipesFragment =
            MyRecipesFragment(viewModel, object : ShowDetailedRecipeListener{
                override fun showDetailedRecipe(recipe: RecipeItem) {
                    val fragment = RecipeDetailFragment(recipe)
                    setCurrentFragment(fragment)
                }
            })
        val addRecipeFragment =
            AddRecipeFragment(viewModel, myRecipesFragment)
        setCurrentFragment(myRecipesFragment)
        setBottomNavigationMenu(myRecipesFragment, addRecipeFragment)
    }

    private fun setBottomNavigationMenu(firstFragment: Fragment, secondFragment: Fragment){
        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.myRecipes -> setCurrentFragment(firstFragment)
                R.id.addRecipe -> setCurrentFragment(secondFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.flFragment, fragment)
            commit()
        }

}