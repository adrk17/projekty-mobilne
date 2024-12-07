package com.example.foodex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodex.model.Food
import com.example.foodex.model.FoodDataProvider

class FoodExViewModel : ViewModel(){
    // TODO: Odseparuj logikę biznesową z MainActivity implementując ViewModel.
    // 1. Przenieś zarządzanie listą `slides` do ViewModel.
    // 2. Przenieś aktualny indeks (`currentIndex`) i funkcję `updateFood()` do ViewModel.
    // 3. Zapewnij dostęp do aktualnego slajdu za pomocą LiveData, aby View (MainActivity) mogło obserwować zmiany.
    // 4. Dodaj funkcję `moveSlide(direction: Direction)` do ViewModel, która obsłuży logikę zmiany slajdów.
}