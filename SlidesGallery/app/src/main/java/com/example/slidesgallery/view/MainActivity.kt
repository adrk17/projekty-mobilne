package com.example.slidesgallery.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.example.slidesgallery.databinding.ActivityMainBinding
import com.example.slidesgallery.model.Slide
import com.example.slidesgallery.model.SlidesDataProvider


class MainActivity : AppCompatActivity() {
    // TODO: Refaktoryzuj MainActivity, aby delegowała logikę biznesową do SlidesViewModel zgodnie ze wzorcem MVVM.
    // 1. Usuń logikę zarządzania slajdami z MainActivity.
    // 2. Obserwuj LiveData z ViewModel, aby aktualizować widoki (np. obraz w ImageView).
    // 3. Przenieś wszelkie zmienne i metody związane z logiką obsługi slajdów do ViewModel.

    private lateinit var layoutBinding: ActivityMainBinding
    //private val viewModel : SlidesViewModel by viewModels()

    private val currentSlide = MutableLiveData<Slide>()
    private val slides = SlidesDataProvider().getSlides()
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

        updateSlide(0)
    }

    override fun onResume() {
        super.onResume()

        getCurrentSlide().observe(this, { slide ->
            layoutBinding.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(resources, slide.photo, applicationContext.theme)
            )
        })
    }

    fun getCurrentSlide(): MutableLiveData<Slide> {
        return currentSlide;
    }

    fun moveSlide(direction: Direction){
        when(direction){
            Direction.LEFT -> updateSlide(currentIndex-1)
            Direction.RIGHT -> updateSlide(currentIndex+1)
        }
    }

    private fun updateSlide(index : Int){
        currentIndex = (index + slides.size) % slides.size
        currentSlide.value = slides[currentIndex]
    }

    enum class Direction(){
        LEFT,
        RIGHT
    }
}