package br.edu.ifsp.dmo.recipemanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipeNameEditText: EditText
    private lateinit var ingredientsEditText: EditText
    private lateinit var instructionsEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var closeButton: Button
    private var recipe: Recipe? = null
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        recipeNameEditText = findViewById(R.id.recipe_name)
        ingredientsEditText = findViewById(R.id.ingredients)
        instructionsEditText = findViewById(R.id.instructions)
        saveButton = findViewById(R.id.save_button)
        closeButton = findViewById(R.id.close_button)

        recipe = intent.getSerializableExtra("recipe") as Recipe?
        position = intent.getIntExtra("position", -1)

        recipe?.let {
            recipeNameEditText.setText(it.name)
            ingredientsEditText.setText(it.ingredients)
            instructionsEditText.setText(it.instructions)
        }

        saveButton.setOnClickListener { saveRecipe() }
        closeButton.setOnClickListener { finish() }
    }

    private fun saveRecipe() {
        val name = recipeNameEditText.text.toString()
        val ingredients = ingredientsEditText.text.toString()
        val instructions = instructionsEditText.text.toString()

        if (recipe == null) {
            recipe = Recipe(name, ingredients, instructions, R.drawable.ic_recipe)
        } else {
            recipe!!.name = name
            recipe!!.ingredients = ingredients
            recipe!!.instructions = instructions
        }

        val resultIntent = Intent()
        resultIntent.putExtra("recipe", recipe)
        resultIntent.putExtra("position", position)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
