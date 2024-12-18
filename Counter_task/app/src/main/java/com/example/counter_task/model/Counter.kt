package com.example.counter_task

data class Counter(
    val value: Int = 0
) {
    fun increment(): Counter = copy(value = value + 1)
    fun decrement(): Counter = copy(value = value - 1)
    fun reset(): Counter = copy(value = 0)
} 