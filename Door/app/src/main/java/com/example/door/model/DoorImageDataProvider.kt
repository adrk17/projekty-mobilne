package com.example.door.model

import com.example.door.R

object DoorImageDataProvider {
    fun getDoorImage(isOpen: Boolean): Int {
        return if (isOpen) {
             R.drawable.open_door
        } else {
            R.drawable.closed_door
        }
    }
}