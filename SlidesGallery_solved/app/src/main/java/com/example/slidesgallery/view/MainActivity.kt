package com.example.slidesgallery.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.slidesgallery.databinding.ActivityMainBinding
import com.example.slidesgallery.viewmodel.Direction
import com.example.slidesgallery.viewmodel.SlidesViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var layoutBinding: ActivityMainBinding
    private val viewModel : SlidesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(layoutBinding.root)

        layoutBinding.buttonLeft.setOnClickListener {
            viewModel.moveSlide(Direction.LEFT)
        }

        layoutBinding.buttonRight.setOnClickListener {
            viewModel.moveSlide(Direction.RIGHT)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.currentSlide.observe(this, { slide ->
            layoutBinding.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(resources, slide.photo, applicationContext.theme)
            )
        })
    }
}