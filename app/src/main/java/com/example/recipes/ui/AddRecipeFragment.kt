package com.example.recipes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.data.db.entities.RecipeItem
import com.example.recipes.ui.dialogs.AddIngredientDialog
import com.example.recipes.ui.dialogs.AddStepDialog
import com.example.recipes.ui.interfaces.AddIngredientListener
import com.example.recipes.ui.interfaces.AddStepListener
import com.example.recipes.ui.rvAdapters.IngredientAdapter
import com.example.recipes.ui.rvAdapters.StepAdapter
import com.example.recipes.ui.rvClasses.Ingredient
import kotlinx.android.synthetic.main.fragment_add_recipe.*

class AddRecipeFragment(
    private val viewModel: RecipeViewModel,
    val myRecipesFragment: MyRecipesFragment

    ) : Fragment() {

    lateinit var ingredients: MutableList<Ingredient>
    lateinit var steps: MutableList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
    }

    override fun onStart() {
        super.onStart()

        setRecyclerViews()
        setListeners()
    }

    private fun setListeners(){
        btnAdd.setOnClickListener {
            addRecipeToDatabase(viewModel)
        }

        fabAddIngredient.setOnClickListener {
            context?.let { AddIngredientDialog(it, object : AddIngredientListener{
                override fun onAddButtonClicked(ingredient: Ingredient) {
                    ingredients.add(ingredient)
                    rvIngredients.adapter?.notifyDataSetChanged()
                }
            }).show() }
        }

        fabAddStep.setOnClickListener {
            context?.let { AddStepDialog(it, object : AddStepListener {
                override fun onAddButtonClicked(step: String) {
                    steps.add(step)
                    rvSteps.adapter?.notifyDataSetChanged()
                }
            }).show() }
        }
    }

    private fun addRecipeToDatabase(viewModel: RecipeViewModel){
        val name = etName.text.toString()
        val kcal = etKcal.text.toString().toIntOrNull()
        val category = spCategories.selectedItem.toString()

        if(name.isEmpty() || kcal == null){
            Toast.makeText(context?.applicationContext, "Dodaj informacje o nazwie lub kaloryczności", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.upsert(RecipeItem(
            name,
            kcal,
            category,
            steps,
            ingredients
        ))

        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.flFragment, myRecipesFragment)
        transaction?.commit()
    }

    private fun setRecyclerViews(){
        setIngredientsRV()
        setStepsRV()
    }

    private fun setIngredientsRV(){
        ingredients = mutableListOf()
        val adapter = IngredientAdapter(ingredients)
        rvIngredients.adapter = adapter
        rvIngredients.layoutManager = LinearLayoutManager(activity)
    }

    private fun setStepsRV(){
        steps = mutableListOf()
        val adapter = StepAdapter(steps)
        rvSteps.adapter = adapter
        rvSteps.layoutManager = LinearLayoutManager(activity)
    }
}