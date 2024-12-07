package com.example.counter_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counter = MutableLiveData<Counter>()
    
    val counter: LiveData<Counter> = _counter

    init {
        // TODO: Zainicjalizuj licznik wartością początkową
    }

    fun increment() {
        // TODO: Pobierz aktualną wartość licznika i zwiększ ją o 1
        // TODO: Zaktualizuj wartość w LiveData
    }

    fun decrement() {
        // TODO: Pobierz aktualną wartość licznika i zmniejsz ją o 1
        // TODO: Zaktualizuj wartość w LiveData
    }

    fun reset() {
        // TODO: Ustaw wartość licznika na 0
        // TODO: Zaktualizuj wartość w LiveData
    }
} 