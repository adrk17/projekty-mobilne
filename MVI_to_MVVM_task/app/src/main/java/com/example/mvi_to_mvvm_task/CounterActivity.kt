package com.example.mvi_to_mvvm_task

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mvi_to_mvvm.mvi.CounterIntent
import com.example.mvi_to_mvvm_task.databinding.ActivityCounterBinding
import kotlinx.coroutines.launch

class CounterActivity : AppCompatActivity() {
    private val viewModel: CounterViewModel by viewModels()
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listeners
        binding.incrementButton.setOnClickListener {
            viewModel.processIntent(CounterIntent.Increment)
        }

        binding.decrementButton.setOnClickListener {
            viewModel.processIntent(CounterIntent.Decrement)
        }

        binding.resetButton.setOnClickListener {
            viewModel.processIntent(CounterIntent.Reset)
        }

        // Observe state
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                updateUI(state)
            }
        }
    }

    private fun updateUI(state: CounterState) {
        binding.countTextView.text = state.count.toString()
    }
} 