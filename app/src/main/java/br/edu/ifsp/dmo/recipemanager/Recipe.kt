package br.edu.ifsp.dmo.recipemanager

import java.io.Serializable

data class Recipe(var name: String, var ingredients: String, var instructions: String, var imageResource: Int = R.drawable.ic_recipe) : Serializable
