package com.example.recipes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipes.data.db.entities.RecipeItem
import com.example.recipes.data.db.typeConverters.IngredientTypeConverter
import com.example.recipes.data.db.typeConverters.StepTypeConverter

@Database(
    entities = [RecipeItem::class],
    version = 1
)
@TypeConverters(IngredientTypeConverter::class,
                StepTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao

    companion object{
        @Volatile
        private var instance: RecipeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
                instance
                    ?: createDatabase(
                        context
                    ).also{
                        instance = it
                    }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            RecipeDatabase::class.java, "Recipedb.db").build()
    }
}