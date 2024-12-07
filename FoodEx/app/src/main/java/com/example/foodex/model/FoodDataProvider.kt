package com.example.foodex.model

import com.example.foodex.R

class FoodDataProvider {
    private val foods = arrayListOf<Food>()

    init {
        foods.add(Food(R.drawable.keb))
        foods.add(Food(R.drawable.keb2))
        foods.add(Food(R.drawable.keb3))
        foods.add(Food(R.drawable.keb4))
        foods.add(Food(R.drawable.keb5))
        foods.add(Food(R.drawable.keb6))
    }

     fun getFoods(): ArrayList<Food> {
        return foods;
    }
}