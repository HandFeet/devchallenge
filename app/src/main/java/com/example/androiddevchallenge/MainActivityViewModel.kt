/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {

    private val coroutine = CoroutineScope(Dispatchers.IO)
    private val getAllDogs = GetAllDogsAction()
    private val getDog = GetDogAction()

    val dogs = MutableLiveData<List<Dog>>()
    val dog = MutableLiveData<Event<Dog>>()

    fun loadDogs() {
        coroutine.launch {
            delay(3000) // Pretend we're loading data
            dogs.postValue(getAllDogs.invoke())
        }
    }

    fun getDog(id: Long) {
        coroutine.launch {
            delay(500) // Pretend we're loading data
            dog.postValue(Event(getDog.invoke(id)))
        }
    }
}
