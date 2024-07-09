package br.edu.ifsp.dmo.recipemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class RecipeAdapter(private val context: Context, private val recipeList: List<Recipe>) : BaseAdapter() {

    override fun getCount(): Int = recipeList.size

    override fun getItem(position: Int): Any = recipeList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false)
        val recipe = recipeList[position]
        val imageView = view.findViewById<ImageView>(R.id.recipe_image)
        val textView = view.findViewById<TextView>(R.id.recipe_name)
        imageView.setImageResource(recipe.imageResource)
        textView.text = recipe.name
        return view
    }
}
