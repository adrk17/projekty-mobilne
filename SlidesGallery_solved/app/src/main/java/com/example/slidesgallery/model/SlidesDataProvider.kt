package com.example.slidesgallery.model

import com.example.slidesgallery.R

class SlidesDataProvider {
    private val slides = arrayListOf<Slide>()

    init {
        slides.add(Slide(R.drawable.keb))
        slides.add(Slide(R.drawable.keb2))
        slides.add(Slide(R.drawable.keb3))
        slides.add(Slide(R.drawable.keb4))
        slides.add(Slide(R.drawable.keb5))
        slides.add(Slide(R.drawable.keb6))
    }

     fun getSlides(): ArrayList<Slide> {
        return slides;
    }
}