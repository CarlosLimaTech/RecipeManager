package br.edu.ifsp.dmo.recipemanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var addButton: Button
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.recipe_list)
        addButton = findViewById(R.id.add_button)

        adapter = RecipeAdapter(this, RecipeRepository.recipes)
        listView.adapter = adapter

        addButton.setOnClickListener {
            val intent = Intent(this, RecipeDetailActivity::class.java)
            startActivityForResult(intent, 1)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("recipe", RecipeRepository.recipes[position])
            intent.putExtra("position", position)
            startActivityForResult(intent, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val recipe = data.getSerializableExtra("recipe") as Recipe
            when (requestCode) {
                1 -> RecipeRepository.recipes.add(recipe)
                2 -> {
                    val position = data.getIntExtra("position", -1)
                    if (position != -1) {
                        RecipeRepository.recipes[position] = recipe
                    }
                }
            }
            adapter.notifyDataSetChanged()
        }
    }
}
