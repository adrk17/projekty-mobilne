package com.example.foodex.view

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.foodex.R
import com.example.foodex.databinding.ActivityMainBinding
import com.example.foodex.model.Food
import com.example.foodex.model.FoodDataProvider
import com.example.foodex.viewmodel.FoodExViewModel

class MainActivity : AppCompatActivity() {
    // TODO: Refaktoryzuj MainActivity, aby delegowała logikę biznesową do FoodExViewModel zgodnie ze wzorcem MVVM.
    // 1. Usuń logikę zarządzania slajdami z MainActivity.
    // 2. Obserwuj LiveData z ViewModel, aby aktualizować widoki (np. obraz w ImageView).
    // 3. Przenieś wszelkie zmienne i metody związane z logiką obsługi slajdów do ViewModel.

    private lateinit var layoutBinding: ActivityMainBinding
    //private val viewModel : FoodExViewModel by viewModels()

    private val currentSlide = MutableLiveData<Food>()
    private val slides = FoodDataProvider().getFoods()
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(layoutBinding.root)

        layoutBinding.buttonLeft.setOnClickListener {
            moveSlide(Direction.LEFT)
        }

        layoutBinding.buttonRight.setOnClickListener {
            moveSlide(Direction.RIGHT)
        }

        updateFood(0)
    }

    override fun onResume() {
        super.onResume()

        getFood().observe(this, { food ->
            layoutBinding.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(resources, food.photo, applicationContext.theme)
            )
        })
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