package com.example.foodex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodex.model.Food
import com.example.foodex.model.FoodDataProvider

class FoodExViewModel : ViewModel(){
    private val currentSlide = MutableLiveData<Food>()
    private val slides = FoodDataProvider().getFoods()

    private var currentIndex = 0

    init {
        updateFood(0)
    }

    fun getFood(): MutableLiveData<Food> {
        return currentSlide;
    }

    fun moveSlide(direction: Direction){
        when(direction){
            Direction.LEFT -> updateFood(currentIndex-1)
            Direction.RIGHT -> updateFood(currentIndex+1)
        }
    }

    private fun updateFood(index : Int){
        currentIndex = (index + slides.size) % slides.size
        currentSlide.value = slides[currentIndex]
    }

    enum class Direction(){
        LEFT,
        RIGHT
    }
}