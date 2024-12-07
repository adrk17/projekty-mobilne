package com.example.counter_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counter = MutableLiveData<Counter>()
    
    val counter: LiveData<Counter> = _counter

    init {
        _counter.value = Counter()
    }

    fun increment() {
        _counter.value = _counter.value?.increment()
    }

    fun decrement() {
        _counter.value = _counter.value?.decrement()
    }

    fun reset() {
        _counter.value = _counter.value?.reset()
    }
} 