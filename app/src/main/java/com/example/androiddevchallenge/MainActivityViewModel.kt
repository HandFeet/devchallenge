package com.example.androiddevchallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.core.actions.GetAllDogsAction
import com.example.androiddevchallenge.core.actions.GetDogAction
import com.example.androiddevchallenge.core.types.Dog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel(app : Application) : AndroidViewModel(app) {

    private val coroutine = CoroutineScope(Dispatchers.IO)
    private val getAllDogs = GetAllDogsAction()
    private val getDog = GetDogAction()

    val dogs = MutableLiveData<List<Dog>>()
    val dog = MutableLiveData<Event<Dog>>()

    fun loadDogs() {
        coroutine.launch {
            delay(3000) //Pretend we're loading data
            dogs.postValue(getAllDogs.invoke())
        }
    }

    fun getDog(id : Long) {
        coroutine.launch {
            delay(500) //Pretend we're loading data
            dog.postValue(Event(getDog.invoke(id)))
        }
    }

}