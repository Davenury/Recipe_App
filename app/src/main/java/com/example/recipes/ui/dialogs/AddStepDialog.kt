package com.example.recipes.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.recipes.R
import com.example.recipes.ui.interfaces.AddStepListener
import com.example.recipes.ui.rvClasses.Ingredient
import kotlinx.android.synthetic.main.dialog_add_ingredient.*
import kotlinx.android.synthetic.main.dialog_add_step.*

class AddStepDialog(context: Context,
    var addStepListener: AddStepListener
) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_step)

        tvDialogStepAdd.setOnClickListener {
            val step = etDialogAddStepName.text.toString()

            if(step.isEmpty()){
                Toast.makeText(context, "Podaj wszystkie informacje",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            addStepListener.onAddButtonClicked(step)
            dismiss()
        }

        tvDialogStepCancel.setOnClickListener {
            cancel()
        }
    }
}