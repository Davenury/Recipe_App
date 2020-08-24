package com.example.recipes.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.recipes.R
import com.example.recipes.ui.interfaces.AddIngredientListener
import com.example.recipes.ui.rvClasses.Ingredient
import kotlinx.android.synthetic.main.dialog_add_ingredient.*

class AddIngredientDialog(
    context: Context,
    var addIngredientListener: AddIngredientListener
) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_ingredient)

        dialogAddIngTVAdd.setOnClickListener {
            val name = dialogAddIngETName.text.toString()
            val quantity = dialogAddIngETAmount.text.toString()
            val measurement = dialogAddIngETMeasurement.text.toString()

            if(name.isEmpty() || quantity.isEmpty() || measurement.isEmpty()){
                Toast.makeText(context, "Podaj wszystkie informacje",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ingredient = Ingredient(name, quantity.toDouble(), measurement)
            addIngredientListener.onAddButtonClicked(ingredient)
            dismiss()
        }

        dialogAddIngTVCancel.setOnClickListener {
            cancel()
        }
    }
}