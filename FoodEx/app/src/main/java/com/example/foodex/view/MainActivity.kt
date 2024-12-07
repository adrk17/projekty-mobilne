package com.example.foodex.view

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.foodex.R
import com.example.foodex.databinding.ActivityMainBinding
import com.example.foodex.viewmodel.FoodExViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var layoutBinding: ActivityMainBinding
    private val viewModel : FoodExViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(layoutBinding.root)

        layoutBinding.buttonLeft.setOnClickListener {
            viewModel.moveSlide(FoodExViewModel.Direction.LEFT)
        }

        layoutBinding.buttonRight.setOnClickListener {
            viewModel.moveSlide(FoodExViewModel.Direction.RIGHT)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getFood().observe(this, Observer { food ->
            layoutBinding.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(resources, food.photo, applicationContext.theme)
            )
        })
    }
}