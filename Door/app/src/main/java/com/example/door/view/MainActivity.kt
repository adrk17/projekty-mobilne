package com.example.door.view

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.door.R
import com.example.door.databinding.ActivityMainBinding
import com.example.door.viewmodel.DoorViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val doorViewModel : DoorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
    }

    override fun onResume() {
        super.onResume()

        doorViewModel.door.observe(this, { door ->
            binding.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(resources, door.photo, applicationContext.theme)
            )
        })

        binding.imageView.setOnClickListener {
            doorViewModel.toggleDoor()
        }
    }
}