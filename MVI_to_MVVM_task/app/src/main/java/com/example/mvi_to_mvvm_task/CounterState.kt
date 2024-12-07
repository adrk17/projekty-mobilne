package com.example.mvi_to_mvvm_task

data class CounterState(
    val count: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
) 