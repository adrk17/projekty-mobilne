package com.example.counter_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.counter_task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: CounterViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        // TODO: Zaobserwuj counter LiveData z ViewModelu
        // TODO: W observe ustaw nową wartość w binding.counterText
    }

    private fun setupClickListeners() {
        // TODO: Dodaj obsługę kliknięć przycisków:
        // TODO: binding.incrementButton powinien wywoływać viewModel.increment()
        // TODO: binding.decrementButton powinien wywoływać viewModel.decrement()
        // TODO: binding.resetButton powinien wywoływać viewModel.reset()
    }
}