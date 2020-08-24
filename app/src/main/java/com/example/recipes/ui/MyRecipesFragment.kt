package com.example.recipes.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.ui.interfaces.ShowDetailedRecipeListener
import com.example.recipes.ui.rvAdapters.RecipeAdapter
import kotlinx.android.synthetic.main.fragment_my_recipes.*

class MyRecipesFragment(
    private val viewModel: RecipeViewModel,
    val showDetailedRecipeListener: ShowDetailedRecipeListener
) : Fragment(R.layout.fragment_my_recipes) {

    override fun onStart() {
        super.onStart()

        setRecycleView()
    }

    private fun setRecycleView() {
        val adapter = RecipeAdapter(listOf(), viewModel, showDetailedRecipeListener)
        rvRecipes.layoutManager = LinearLayoutManager(activity)
        rvRecipes.adapter = adapter

        //var selectedCategory = spCategoriesWithEverything.selectedItem.toString()

        spCategoriesWithEverything.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>?,
                                        view: View?,
                                        position: Int, id: Long) {
                val item = adapterView?.getItemAtPosition(position).toString()

                if(item == "Wszystko"){
                    viewModel.getAllRecipes().observe(this@MyRecipesFragment, Observer {
                        adapter.recipes = it
                        adapter.notifyDataSetChanged()
                    })
                }
                else {
                    viewModel.getRecipesFromCategory(item).observe(this@MyRecipesFragment, Observer {
                        adapter.recipes = it
                        adapter.notifyDataSetChanged()
                    })
                }
            }
        }
    }
}