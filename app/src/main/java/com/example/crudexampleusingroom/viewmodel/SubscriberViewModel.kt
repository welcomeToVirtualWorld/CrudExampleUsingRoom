package com.example.crudexampleusingroom.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudexampleusingroom.Event
import com.example.crudexampleusingroom.Presenter
import com.example.crudexampleusingroom.db.Subscribers
import com.example.crudexampleusingroom.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveButton = MutableLiveData<String>()

    @Bindable
    val clearButton = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
    get() = statusMessage

    init {
        saveButton.value = "Save"
        clearButton.value = "Clear"
    }

    fun saveOrUpdate() {
        if (saveButton.value == "Save"){
            Log.i("Click", "Button Clicked")
            val name: String = inputName.value!!
            val email: String = inputEmail.value!!
            insert(Subscribers(name, email))
            inputName.value = null
            inputEmail.value = null
        }else{
            val name: String = inputName.value!!
            val email: String = inputEmail.value!!
            update(Subscribers(name, email))
            inputName.value = null
            inputEmail.value = null
        }

    }

    fun updateOrDelete(sub: Subscribers){
        inputName.value = sub.subscriber_name
        inputEmail.value = sub.subscriber_email
        saveButton.value = "Update"
        clearButton.value = "Delete"
    }

    fun clearAllOrDelete() {
        if (clearButton.value == "Clear"){
            clearALL()
        }else{
            val name: String = inputName.value!!
            val email: String = inputEmail.value!!
            delete(Subscribers(name, email))
            inputName.value = null
            inputEmail.value = null
        }

    }

    fun insert(sub: Subscribers) {
        viewModelScope.launch {
            repository.insert(sub)
            statusMessage.value = Event("Subscriber Inserted Successfully")
        }
    }

    fun update(sub: Subscribers) {
        viewModelScope.launch {
            repository.update(sub)
            statusMessage.value = Event("Subscriber updated Successfully")
        }
    }

    fun delete(sub: Subscribers) {
        viewModelScope.launch {
            repository.delete(sub)
            statusMessage.value = Event("Subscriber deleted Successfully")
        }
    }

    fun clearALL() {
        viewModelScope.launch {
            repository.deleteAll()
            statusMessage.value = Event("All Subscriber deleted Successfully")
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}