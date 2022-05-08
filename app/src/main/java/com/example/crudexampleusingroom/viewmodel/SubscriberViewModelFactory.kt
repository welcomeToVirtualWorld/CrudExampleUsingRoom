package com.example.crudexampleusingroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudexampleusingroom.databinding.ActivityMainBinding
import com.example.crudexampleusingroom.repository.SubscriberRepository
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class SubscriberViewModelFactory(private val repository: SubscriberRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view Model class")
    }
}