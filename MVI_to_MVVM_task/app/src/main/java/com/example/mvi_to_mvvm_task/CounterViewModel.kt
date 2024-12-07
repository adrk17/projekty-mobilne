package com.example.mvi_to_mvvm_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_to_mvvm.mvi.CounterIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {
    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state

    fun processIntent(intent: CounterIntent) {
        viewModelScope.launch {
            when (intent) {
                is CounterIntent.Increment -> {
                    _state.value = _state.value.copy(count = _state.value.count + 1)
                }
                is CounterIntent.Decrement -> {
                    _state.value = _state.value.copy(count = _state.value.count - 1)
                }
                is CounterIntent.Reset -> {
                    _state.value = _state.value.copy(count = 0)
                }
            }
        }
    }
} 