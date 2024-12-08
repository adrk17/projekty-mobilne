package com.example.door.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.door.model.Door
import com.example.door.model.DoorImageDataProvider

class DoorViewModel : ViewModel() {
    private val _door = MutableLiveData<Door>()
    val door : LiveData<Door> = _door

    init {
        _door.value = Door(
            isOpen = false,
            photo = DoorImageDataProvider.getDoorImage(false)
        )
    }

    fun toggleDoor() {
        _door.value = _door.value?.let {
            Door(
                isOpen = !it.isOpen,
                photo = DoorImageDataProvider.getDoorImage(!it.isOpen)
            )
        }
    }
}