package com.example.slidesgallery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.slidesgallery.model.Slide
import com.example.slidesgallery.model.SlidesDataProvider


class SlidesViewModel : ViewModel(){
    private val currentSlide = MutableLiveData<Slide>()
    private val slides = SlidesDataProvider().getSlides()
    private var currentIndex = 0

    init {
        updateSlide(0)
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

}