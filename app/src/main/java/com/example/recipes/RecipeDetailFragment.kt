package com.example.recipes

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.recipes.data.db.entities.RecipeItem
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import kotlinx.android.synthetic.main.item_ingredient_detail.view.*
import kotlinx.android.synthetic.main.item_step_detail.view.*

class RecipeDetailFragment(
    private val recipe: RecipeItem
) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onStart() {
        super.onStart()

        tvRecipeDetailName.text = recipe.name
        tvRecipeDetailCategory.text = recipe.category
        tvRecipeDetailKcal.text = "${recipe.kcal}"

        val tvIngredients = TextView(activity).apply{
            this.text = "Składniki"
            this.textSize = 16F
        }
        llRecipeDetail.addView(tvIngredients)

        for(ingredient in recipe.ingredients){
            val ingView = LayoutInflater.from(activity).inflate(R.layout.item_ingredient_detail, llRecipeDetail, false)

            ingView.tvIngDetailName.text = ingredient.name
            ingView.tvIngDetailAmount.text = "${ingredient.quantity}"
            ingView.tvIngDetailMeasurement.text = ingredient.measurement

            llRecipeDetail.addView(ingView)
        }

        val tvSteps = TextView(activity).apply{
            this.text = "Wykonanie"
            this.textSize = 16F
        }
        llRecipeDetail.addView(tvSteps)

        for((index, step) in recipe.steps.withIndex()){
            val stepView = LayoutInflater.from(activity).inflate(R.layout.item_step_detail, llRecipeDetail, false)

            stepView.tvStepDetailNumber.text = "${index + 1}."
            stepView.tvStepDetailStep.text = step

            llRecipeDetail.addView(stepView)
        }
    }

}