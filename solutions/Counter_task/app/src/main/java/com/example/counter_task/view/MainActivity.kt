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
        viewModel.counter.observe(this) { count ->
            binding.counterText.text = count.value.toString()
        }
    }

    private fun setupClickListeners() {
        binding.incrementButton.setOnClickListener {
            viewModel.increment()
        }
        
        binding.decrementButton.setOnClickListener {
            viewModel.decrement()
        }
        
        binding.resetButton.setOnClickListener {
            viewModel.reset()
        }
    }
}