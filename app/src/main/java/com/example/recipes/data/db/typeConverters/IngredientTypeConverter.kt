package com.example.recipes.data.db.typeConverters

import androidx.room.TypeConverter
import com.example.recipes.ui.rvClasses.Ingredient
import com.google.gson.Gson

class IngredientTypeConverter {

    @TypeConverter
    fun listToJson(value: List<Ingredient>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Ingredient>::class.java).toList()
}