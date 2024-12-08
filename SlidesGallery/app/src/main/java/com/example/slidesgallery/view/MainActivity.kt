package com.example.slidesgallery.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.slidesgallery.databinding.ActivityMainBinding
import com.example.slidesgallery.model.Slide
import com.example.slidesgallery.model.SlidesDataProvider


class MainActivity : AppCompatActivity() {
    // TODO: Refaktoryzuj MainActivity, aby delegowała logikę biznesową do SlidesViewModel zgodnie ze wzorcem MVVM.
    // 1. Usuń logikę zarządzania slajdami z MainActivity.
    // 2. Obserwuj LiveData z ViewModel, aby aktualizować widoki (np. obraz w ImageView).
    // 3. Przenieś wszelkie zmienne i metody związane z logiką obsługi slajdów do ViewModel.
    /*
        com.example.slidesgallery:
        - model
            - Slide.kt
            - SlidesDataProvider.kt
        - view
            - MainActivity.kt TODO: Przenieś logikę obsługi slajdów do ViewModel.
        - viewmodel
            - SlidesViewModel.kt TODO: Utwórz ViewModel, który będzie zarządzał listą slajdów.
     */

    private lateinit var layoutBinding: ActivityMainBinding
    // Utworzenie instancji ViewModel za pomocą delegacji przez viewModels(). W takim przypadku ViewModel jest tworzona i zarządzana przez bibliotekę, zachowując swój stan w większej niezależności od cyklów życia Activity.
    //private val viewModel : SlidesViewModel by viewModels()

    // Zmienna prywatna, typu MutableLiveData, przechowująca aktualny slajd, zmieniana wewnątrz ViewModel.
    private val _currentSlide = MutableLiveData<Slide>()
    // Zmienna publiczna, typu LiveData, niezmienna, przechowująca aktualny slajd do obserwacji w innych częściach aplikacji.
    val currentSlide: LiveData<Slide> = _currentSlide

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

        // Część logiki biznesowej związana z obsługą slajdów.
        updateSlide(0)
    }

    override fun onResume() {
        super.onResume()

        currentSlide.observe(this, { slide ->
            layoutBinding.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(resources, slide.photo, applicationContext.theme)
            )
        })
    }

    fun moveSlide(direction: Direction){
        when(direction){
            Direction.LEFT -> updateSlide(currentIndex-1)
            Direction.RIGHT -> updateSlide(currentIndex+1)
        }
    }

    private fun updateSlide(index : Int){
        currentIndex = (index + slides.size) % slides.size
        _currentSlide.value = slides[currentIndex]
    }

    enum class Direction(){
        LEFT,
        RIGHT
    }
}